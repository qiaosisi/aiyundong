package com.aiyundong.web;

import com.aiyundong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {
    @Autowired
    UserService userService;

    @GetMapping(value = "")
    public String index(ModelMap modelMap){
        List list =  userService.findAll();
        modelMap.put("list",list);
        return "web/index";
    }

}
