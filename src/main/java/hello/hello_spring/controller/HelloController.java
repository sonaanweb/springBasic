package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // hello url 매칭 getmapping
    @GetMapping("hello")
    public String hello(Model model){
        // return `hello`.html 파일 내 ${data}라는 이름으로 value 값 치환
        model.addAttribute("data","hello!!");
        return "hello";
    }

    // 웹에서 param 받을 것
    // Model에 담아줌(뷰에서 랜더링)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name); // key , name
        return "hello-template";
    }

    // API
    // ResponseBody - body 부분에 return 값 반환
    // 문자가 그대로 내려감
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello [spring]"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체
    }

    // static class를 만들면 클래스 안에서 재사용 가능 ex)HelloController.Hello
    static class Hello {
        private String name;

        // 꺼낼 때는 get 넣을 때는 set
        // 자바 빈 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}