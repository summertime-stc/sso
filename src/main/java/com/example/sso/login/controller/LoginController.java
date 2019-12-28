package com.example.sso.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Api(tags = "sso")
public class LoginController {


    @ApiOperation(value = "遍历json")
    @RequestMapping("/test001")
    public String test1(){
        return "test";
    }
}
