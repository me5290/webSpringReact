package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {
    // 스프링이 우선적으로 모든 HTTP요청을 핸들하기 때문에 해당 작업이 필요
    // 스프링과 리액트가 통합되었을때 리액트의 컴포넌트를 매핑하기 위해서 필요한 매핑작업
    @GetMapping(value = {"/","/chatting","/member/signup","/member/login","/board/write","/board"})
    public String ReactFoward(){
        return "forward:/index.html"; // 리액트 페이지
    }
}
