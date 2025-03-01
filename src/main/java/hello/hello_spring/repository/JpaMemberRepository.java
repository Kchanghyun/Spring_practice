package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA를 사용하려면 EntityManager를 주입받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        // 이게 끝...?
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK(Primary Key) 기반이 아닌 것들은 JPQL 언어를 작성해줘야 함.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL이라는 쿼리 언어인데 테이블 대상으로 쿼리를 날리는 게 아닌 객체(Entity)를 대상으로 쿼리를 날림. -> SQL로 번역이 됨.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
