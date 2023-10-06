package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserPasswordParam;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Admin> {
    AjaxRes<Admin> login(HttpServletRequest request, Admin admin);

    AjaxRes<String> logout(HttpServletRequest request);

    AjaxRes<Admin> signup(HttpServletRequest request, Admin admin);

    AjaxRes<Admin> updateAdmin(Admin admin);

    boolean uniqueUsername(String username);

    AjaxRes<Admin> changePassword(UserPasswordParam param);

    AjaxRes<String> changeAdminStatus(Long adminId,HttpServletRequest request);
}
