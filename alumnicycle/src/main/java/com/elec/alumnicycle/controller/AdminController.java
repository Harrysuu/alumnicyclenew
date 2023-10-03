package com.elec.alumnicycle.controller;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "Admin Page ")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "admin login")
    public AjaxRes<Admin> login(HttpServletRequest request, @RequestBody Admin admin){
        return adminService.login(request, admin);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "admin logout")
    public AjaxRes<String> logout(HttpServletRequest request){
        return adminService.logout(request);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "signup a new admin")
    public AjaxRes<Admin> addNewUser(HttpServletRequest request, @RequestBody Admin admin){
        return adminService.signup(request, admin);
    }

    @PostMapping("/updateAdmin")
    @ApiOperation(value = "update admin")
    public AjaxRes<Admin> updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @GetMapping("/uniqueUsernameCheck")
    @ApiOperation(value = "check uniquness of the admin")
    public boolean uniqueUsername(String username){
        return adminService.uniqueUsername(username);
    }

    @PostMapping ("/changePassword")
    @ApiOperation(value = "change admin password")
    public AjaxRes<Admin> changePassword(@RequestBody UserPasswordParam param){
        return adminService.changePassword(param);
    }

    @GetMapping ("/changeUserStatus")
    @ApiOperation(value = "change user status")
    public AjaxRes<String> changeUserStatus(Long userId){
        return userService.changeUserStatus(userId);
    }


}
