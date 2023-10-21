package com.elec.alumnicycle.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.LoginParam;
import com.elec.alumnicycle.entity.params.UserParam;
import com.elec.alumnicycle.entity.params.UserPasswordParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface UserService extends IService<User> {


    AjaxRes<User> login(HttpServletRequest request, HttpSession session, LoginParam loginparam);

    AjaxRes<String> logout(HttpServletRequest request);

    AjaxRes<User> signup(HttpServletRequest request, User user);

    AjaxRes<Page<User>> getUserByPage(UserParam param);

    AjaxRes<User> getUserById(Long userId);

    AjaxRes<User> updateUser(HttpServletRequest request, User user);

    boolean uniqueUsername(String username);

    AjaxRes<User> addCredit(HttpServletRequest request, double point);

    AjaxRes<User> changePassword(HttpServletRequest request, UserPasswordParam param);

    AjaxRes<String> changeUserStatus(Long userId);

    AjaxRes<String> loginByPhone(HttpSession session, String phoneNumber);
}
