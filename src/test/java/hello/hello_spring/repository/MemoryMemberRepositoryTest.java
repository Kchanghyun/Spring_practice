package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 할 떄 순서는 컴퓨터 멋대로임. -> 순서에 영향을 받는 코드를 짜면 안된다.
    // 메서드 동작 끝날 때마다 실행되는 어노테이션
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 기대한 값은 member와 동일한 어떤 데이터인데, 실제 들어온 값은 result -> 만약 같으면 Test 성공, 다르면 Test Error
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 멤버 2개 기준 2 -> Test 통과
        assertThat(result.size()).isEqualTo(2);
        // 3 -> Test 탈락
//        assertThat(result.size()).isEqualTo(3);
    }
}
