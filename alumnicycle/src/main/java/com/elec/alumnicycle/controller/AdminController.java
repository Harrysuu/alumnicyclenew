package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Administrator;
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
    public AjaxRes<Administrator> login(HttpServletRequest request, @RequestBody Administrator administrator){
        return adminService.login(request, administrator);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "admin logout")
    public AjaxRes<String> logout(HttpServletRequest request){
        return adminService.logout(request);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "signup a new admin")
    public AjaxRes<Administrator> addNewUser(HttpServletRequest request, @RequestBody Administrator administrator){
        return adminService.signup(request, administrator);
    }

    @PostMapping("/updateAdmin")
    @ApiOperation(value = "update admin")
    public AjaxRes<Administrator> updateAdmin(HttpServletRequest request, @RequestBody Administrator administrator){
        return adminService.updateAdmin(request, administrator);
    }

    @GetMapping("/uniqueUsernameCheck")
    @ApiOperation(value = "check uniquness of the admin")
    public boolean uniqueUsername(String username){
        return adminService.uniqueUsername(username);
    }

    @PostMapping ("/changePassword")
    @ApiOperation(value = "change admin password")
    public AjaxRes<Administrator> changePassword(HttpServletRequest request, @RequestBody UserPasswordParam param){
        return adminService.changePassword(request, param);
    }

    @GetMapping ("/changeUserStatus")
    @ApiOperation(value = "change user status")
    public AjaxRes<String> changeUserStatus(Long userId){
        return userService.changeUserStatus(userId);
    }

    @GetMapping("/editAdminStatus")
    @ApiOperation(value = "change admin status")
    public AjaxRes<String> changeAdminStatus(Long adminId,HttpServletRequest request){
        return adminService.changeAdminStatus(adminId,request);
    }

    @PostMapping("/getAllAdmin")
    @ApiOperation(value = "get admin data")
    public AjaxRes<Page<Administrator>> getAllAdmin(@RequestBody Page page){
        return adminService.getAllAdmin(page);
    }

}
