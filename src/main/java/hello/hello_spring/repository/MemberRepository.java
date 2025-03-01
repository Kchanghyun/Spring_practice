package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    // Optional -> find~를 통해 데이터를 찾을 때 null을 반환하는 경우 최근 Optional로 감싸서 리턴을 한다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
