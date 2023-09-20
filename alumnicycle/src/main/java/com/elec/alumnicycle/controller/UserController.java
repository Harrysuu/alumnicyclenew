package com.elec.alumnicycle.controller;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "User Page ")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/login")
    @ApiOperation(value = "user login")
    public AjaxRes<User> login(HttpServletRequest request, @RequestBody User user){
        return userService.login(request, user);
    }
}
