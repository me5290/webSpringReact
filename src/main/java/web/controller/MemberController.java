package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.service.MemberService;

import java.util.List;
import java.util.Map;

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
//    @PostMapping("/login/post.do")
//    public boolean doLoginPost(MemberDto memberDto){
//        return memberService.doLoginPost(memberDto);
//    }

    // 3. 로그아웃
//    @GetMapping("/logout/get.do")
//    public boolean doLogoutGet(){
//        return memberService.doLogoutGet();
//    }

    // 4. 내정보
    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }

    // 5. 아이디 중복검사
    @GetMapping("/idConfirm/get.do")
    public boolean doIdConfirm(@RequestParam String id){
        System.out.println("id"+id);
        return memberService.doIdConfirm(id);
    }

    @GetMapping("/find/email/get.do")
    public boolean doFindEmail(String memail){
        return memberService.getFindMemail(memail);
    }

    @GetMapping("/find/myboard/get.do")
    public List<Map<Object,Object>> findByBoardList(){
        return memberService.findMyBoardList();
    }

}