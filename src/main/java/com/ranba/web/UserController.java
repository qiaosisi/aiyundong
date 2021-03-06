package com.ranba.web;

import com.ranba.model.User;
import com.ranba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public List listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }

}
