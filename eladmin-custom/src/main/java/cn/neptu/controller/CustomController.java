package cn.neptu.controller;

import me.zhengjie.annotation.rest.AnonymousGetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @AnonymousGetMapping
    public String hello(){
        return "hi ネプテューヌ";
    }
}
