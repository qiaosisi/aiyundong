package com.aiyundong.web.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyProfileController {

    @GetMapping(value = "/my")
    public String myProfile(){
        return "web/my/profile";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "web/login";
    }
}
