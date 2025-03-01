package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// test 할 때 회원가입을 예로 들자면 중복 회원이 생길 경우 오류가 터진다. -> 테스트 여러번 할 경우 spring name 중복으로 오류가 터짐.
// 이를 방지하기 위해 Transactional을 사용하는데 Transactional은 테스트 완료 후 rollback을 한다. -> 다음 테스트에 영향을 주지 않는다.
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 두 번째 인자로 간 람다에서 IllegalStateException 예외가 터지면 Test 성공
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}