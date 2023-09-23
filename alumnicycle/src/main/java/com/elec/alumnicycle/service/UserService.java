package com.elec.alumnicycle.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserParam;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {


    AjaxRes<User> login(HttpServletRequest request, User user);

    AjaxRes<String> logout(HttpServletRequest request);

    AjaxRes<String> signup(HttpServletRequest request, User user);

    AjaxRes<Page<User>> getUserByPage(UserParam param);

    AjaxRes<User> getUserById(Long userId);

    AjaxRes<User> updateUser(User user);

    boolean uniqueUsername(String username);

    AjaxRes<User> addCredit(double point);
}
