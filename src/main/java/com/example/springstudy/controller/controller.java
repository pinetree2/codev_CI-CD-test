package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controller {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //파라미터를 외부에서 받아와서 model에 담음
        model.addAttribute("name",name);
        return "hello-template";
        //templates - > hello-template로 이동
    }

    @GetMapping("hello-string")
    @ResponseBody //view 리졸버를 사용하지 않고, HTTP BODY에 직접 param 반환
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체 반환시 JSON으로 변환됨
    }

    static class Hello{ //Hello 객체
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
