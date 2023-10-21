package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Administrator;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserPasswordParam;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Administrator> {
    AjaxRes<Administrator> login(HttpServletRequest request, Administrator administrator);

    AjaxRes<String> logout(HttpServletRequest request);

    AjaxRes<Administrator> signup(HttpServletRequest request, Administrator administrator);

    AjaxRes<Administrator> updateAdmin(HttpServletRequest request, Administrator administrator);

    boolean uniqueUsername(String username);

    AjaxRes<Administrator> changePassword(HttpServletRequest request, UserPasswordParam param);

    AjaxRes<String> changeAdminStatus(Long adminId,HttpServletRequest request);

    AjaxRes<Page<Administrator>> getAllAdmin(Page page);

    AjaxRes<Page<User>> getAllUser(Page page);
}
