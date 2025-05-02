package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 정적 resource (index.html)은 무시됨 - mapping 되는 controller가 있으니까?
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
