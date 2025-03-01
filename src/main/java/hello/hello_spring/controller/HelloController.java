package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1. 정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
    // /hello 들어오면 이 메소드 호출해줌
        // MVC - Model, View, Controller
        model.addAttribute("data", "hello!!");
        // key - data, value - hello
        return "hello";
    }

    // 2. MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        // @RequestParam("name") -> 외부에서 값을 받아옴
        // "name" -> key, name -> value
        return "hello-template";
    }

    // 3. API 사용 바로 아래의 return "hello " + name; 같은 방식은 거의 사용하지 않고 객체를 반환하는 방식을 사용함.
    @GetMapping("hello-string")
    @ResponseBody
    // http 헤더와 바디 중 바디부에 리턴 데이터를 직접 넣어주는 거
    // html 없이 그냥 그대로 전송하는 것에서 차이가 있음.
    public String helloString(@RequestParam("name") String name, Model model) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name, Model model) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 객체를 리턴하면 JSON 형식으로 구현
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
