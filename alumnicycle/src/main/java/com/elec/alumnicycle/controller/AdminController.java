package com.elec.alumnicycle.controller;

import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "Admin Page ")
public class AdminController {

    @Autowired
    private AdminService adminService;



}
