package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Spring container가 뜰 때 생성됨
@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberService는 순수한 자바 클래스
    // 1. 생성자 주입 - 가장 권장됨
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    @Autowired // 2. 필드 주입 - 별로 안 좋음
    private final MemberService memberService;

    @Autowired // 3. setter 주입
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    // 단점: 누군가가 memberController를 호출했을 때 public으로 열려 있어야 함
     */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { // form의 name에 input에 입력된 값이 들어옴 (POST Method)
        Member member = new Member();
        member.setName(form.getName()); // 폼에 입력된 이름을 가져옴

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}





