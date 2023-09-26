package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserParam;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
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

    @PostMapping("/login")
    @ApiOperation(value = "user login")
    public AjaxRes<User> login(HttpServletRequest request, @RequestBody User user){
        return userService.login(request, user);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "user logout")
    public AjaxRes<String> logout(HttpServletRequest request){
        return userService.logout(request);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "signup a new user")
    public AjaxRes<User> addNewUser(HttpServletRequest request, @RequestBody User user){
        return userService.signup(request, user);
    }

    @PostMapping("/page")
    @ApiOperation(value = "get user pages")
    public AjaxRes<Page<User>> userPage(@RequestBody UserParam param){
        return userService.getUserByPage(param);
    }

    @GetMapping("/getById")
    @ApiOperation(value = "get user by userId")
    public AjaxRes<User> getUserById(Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "update user")
    public AjaxRes<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/uniqueUsernameCheck")
    @ApiOperation(value = "check uniquness of the username")
    public boolean uniqueUsername(String username){
        return userService.uniqueUsername(username);
    }

    @GetMapping("/addCredit")
    @ApiOperation(value = "add credit")
    public AjaxRes<User> addCredit(double point){
        return userService.addCredit(point);
    }

    @PostMapping ("/changePassword")
    @ApiOperation(value = "change user password")
    public AjaxRes<User> changePassword(@RequestBody UserPasswordParam param){
        return userService.changePassword(param);
    }



}
