package com.ranba.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    // 用于测试token的验证
    @GetMapping("")
    public Object getTest(HttpServletRequest request){
        System.out.println(request.getAttribute("claims"));
        Map<String, String> claims = (Map<String, String>) request.getAttribute("claims");
        return "test";
    }

}

