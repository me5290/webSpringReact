package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
// @CrossOrigin("http://localhost:3000") // 요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    private MemberService memberService;

    // 1. 회원가입
    @PostMapping("/signup/post.do")
    public boolean doSignUpPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignUpPost(memberDto);
    }

    // 2. 로그인
    @PostMapping("/login/post.do")
    public boolean doLoginPost(MemberDto memberDto){
        return memberService.doLoginPost(memberDto);
    }

    // 3. 로그아웃
    @GetMapping("/logout/get.do")
    public boolean doLogoutGet(){
        return memberService.doLogoutGet();
    }

    // 4. 내정보
    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }
}
