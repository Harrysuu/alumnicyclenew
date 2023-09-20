package com.elec.alumnicycle.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.User;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {


    AjaxRes<User> login(HttpServletRequest request, User user);
}
