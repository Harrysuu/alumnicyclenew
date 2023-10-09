package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
import com.elec.alumnicycle.mapper.AdminMapper;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public AjaxRes<Admin> login(HttpServletRequest request, Admin admin) {
        //verify password
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // check username in database
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getUsername, admin.getUsername());
        // as the username is unique, getOne method is applied here
        Admin loginAdmin = this.getOne(lqw);

        //if username is not exist
        if(loginAdmin == null){
            return AjaxRes.failMsg("Admin not exist");
        }

        // check password
        if(!loginAdmin.getPassword().equals(password)){
            return AjaxRes.failMsg("Wrong Password");
        }

        // check user status
        if (loginAdmin.getStatus() == 0){
            return  AjaxRes.failMsg("This admin is blocked");
        }

        // set user id into Session
        Long adminId = loginAdmin.getId();
        request.getSession().setAttribute("Admin",adminId);

        //set userId to BaseContext
        BaseContext.setCurrentId(adminId);

        loginAdmin.setOperationTime(LocalDateTime.now());
        this.updateById(loginAdmin);

        //return success loginUser
        return AjaxRes.success(loginAdmin);

    }

    @Override
    public AjaxRes<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("Admin");
        return AjaxRes.success("Admin Logout successfully");
    }

    @Override
    public AjaxRes<Admin> signup(HttpServletRequest request, Admin admin) {
        // create unique id
        long adminId = IdWorker.getId();

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9@#$%^&+=!]).{8,}$";
        String password = admin.getPassword();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches() || password.length() < 8){
            return AjaxRes.failMsg("password does not meet the requirement");
        }

        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getId,admin.getId());
        if (count(lqw) > 0){
            return AjaxRes.failMsg("username is already exist");
        }
        // set attributes to admin
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        admin.setOperationTime(LocalDateTime.now());
        admin.setStatus(1);

        // save
        this.save(admin);

        // set session
        request.getSession().setAttribute("Admin",admin.getId());

        // return
        return AjaxRes.success(admin);
    }

    @Override
    public AjaxRes<Admin> updateAdmin(Admin admin) {

        Long currentId = BaseContext.getCurrentId();
        currentId = 33L;

        // get currentUser
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getId, currentId);
        Admin currentAdmin = this.getOne(lqw);

        // update username attributes according to frontpage inputs
        currentAdmin.setUsername(admin.getUsername());
//        currentAdmin.setGrade(admin.getGrade());

        this.updateById(currentAdmin);
        return AjaxRes.success(currentAdmin);
    }

    @Override
    public boolean uniqueUsername(String username) {
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getUsername, username);
        int count = this.count(lqw);

        if (count != 0){
            // username already exists
            return false;
        }
        // username not exist
        return true;
    }

    @Override
    public AjaxRes<Admin> changePassword(UserPasswordParam param) {
        // get id from BaseContext
        Long currentId = BaseContext.getCurrentId();
        currentId = 33L;

        // get passwords from param
        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());

        // query user in database
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getId, currentId);
        Admin loginAdmin = this.getOne(lqw);

        // check old password correct or not
        if (! oldPassword.equals(loginAdmin.getPassword())){
            return AjaxRes.failMsg("Wrong current password");
        }

        // set new password
        loginAdmin.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));

        // update and return
        this.updateById(loginAdmin);
        return AjaxRes.success(loginAdmin);
    }

    @Override
    public AjaxRes<String> changeAdminStatus(Long adminId,HttpServletRequest request) {
        Admin adminTarget = this.getById(adminId);
        Long loginId = (Long) request.getSession().getAttribute("Admin");
        Admin adminPresent = this.getById(loginId);
        LambdaUpdateWrapper<Admin> luw = new LambdaUpdateWrapper<>();
        if(adminPresent.getGrade() < adminTarget.getGrade()){
            return AjaxRes.failMsg("Unable to change the administrator status above current account level");
        }else {
            if(adminTarget.getStatus() == 0){
                luw.eq(Admin::getId,adminId)
                        .set(Admin::getStatus,1)
                        .set(Admin::getOperationTime,LocalDateTime.now());
                this.update(luw);
            }else {
                luw.eq(Admin::getId,adminId)
                        .set(Admin::getStatus,0)
                        .set(Admin::getOperationTime,LocalDateTime.now());
                this.update(luw);
            }
            return AjaxRes.success("change has been made");
        }
    }


}
