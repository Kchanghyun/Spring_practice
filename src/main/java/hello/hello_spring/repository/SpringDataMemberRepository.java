package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 받을 때는 implements가 아닌 extends이다.
// 스프링 데이터 JPA가 JpaRepository를 받고 있으면 스프링이 구현체를 자동으로 만들어준다. -> 스프링 빈에 자동으로 등록한다.
public interface SpringDataMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

    /**
     * JpaRepository 에서 기본적으로 CRUD, 기본 id의 조회, 전체 조회 등 기본 기능들을 제공해줌.
     * 따라서 공통적인 인터페이스에서 다루지 않는 value인 name으로 조회하는 메서드만 따로 구현함
     * findBy~~ 메서드 정의만 해놓으면 ~~ 변수를 찾아서 메서드 상단의 sql 쿼리처럼 해석이 되어 메서드를 구현해줌.
     */
}
