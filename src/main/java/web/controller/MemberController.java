package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // 요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping("/signup/post.do")
    public boolean doSignUpPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignUpPost(memberDto);
    }
}
