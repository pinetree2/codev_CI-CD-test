package com.example.springstudy.controller;

import com.example.springstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //이렇게 객체를 따로 만들어버리면 여러번 코드 작성 -> 좀 비효율적
    //private final MemberService memberService = new MemberService();

    //위의 단점 극복 방법 생성자로 연결해준다
    private final MemberService memberService;

    //dependecy injection
    @Autowired // 스프링 컨테이너에서 멤버 서비스를 가져다가 연결 시켜준다. 근데 이 어노테이션에는 이 코드가순수한 자바 코드라서 아무것도 안된다 그래서 서비스 어노테이션 따로 추가함
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
