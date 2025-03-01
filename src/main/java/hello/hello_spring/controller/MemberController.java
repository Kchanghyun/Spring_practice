package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컨테이너가 스프링 참에 뜰 때 컨테이너라는 스프링 통이 생긴다.?
// 스프링 컨테이너가 객체 생성 후 관리를 한다.
@Controller
public class MemberController {
    private final MemberService memberService;

    // memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 조회할 때 주로 사용
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 데이터를 Form같은 데에 넣어서 전달할 때 주로 사
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
        // 회원가입이 끝나면 홈 화면으로 보내기
    }

    @GetMapping("/members")
    // Model -> Spring에서 제공하는 객체로, 컨트롤러에서 데이터를 뷰(View)로 전달할 때 사용
    // "members" -> 뷰에서 사용할 변수 이름(HTML에서 members라는 이름으로 데이터를 참조 가능)
    // members -> 실제로 전달할 데이터
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

/**
 * 컨트롤러는 자바 코드로 따로 컨테이너에 등록을 못함?
 * 그래서 그냥 이대로 써야 함.
 */