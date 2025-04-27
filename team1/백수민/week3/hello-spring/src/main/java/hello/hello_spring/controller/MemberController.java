package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring container가 뜰 때 생성됨
@Controller
public class MemberController {
    private final MemberService memberService;

    // MemberService는 순수한 자바 클래스
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}





