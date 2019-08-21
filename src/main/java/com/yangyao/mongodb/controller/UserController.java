package com.yangyao.mongodb.controller;

import com.yangyao.mongodb.model.User;
import com.yangyao.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("list")
    public String list(Model model){
        List<User> list=userService.findAll();
        model.addAttribute("userList",list);
        return "pages/user/list";
    }

    @RequestMapping("findByID")
    public String findByID(long id){
        userService.findBy(id);
        return "pages/user/list";
    }
}
