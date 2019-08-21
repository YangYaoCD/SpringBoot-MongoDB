package com.yangyao.mongodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("")
    public String index(){
        return "index";
    }
    @RequestMapping("userlogin")
    public String login(){
        return "login";
    }
}
