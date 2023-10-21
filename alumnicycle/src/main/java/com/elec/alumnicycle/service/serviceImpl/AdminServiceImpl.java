package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.Administrator;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
import com.elec.alumnicycle.mapper.AdminMapper;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Administrator> implements AdminService {

    @Override
    public AjaxRes<Administrator> login(HttpServletRequest request, Administrator administrator) {
        //verify password
        String password = administrator.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // check username in database
        LambdaQueryWrapper<Administrator> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Administrator::getUsername, administrator.getUsername());
        // as the username is unique, getOne method is applied here
        Administrator loginAdministrator = this.getOne(lqw);

        //if username is not exist
        if(loginAdministrator == null){
            return AjaxRes.failMsg("Admin not exist");
        }

        // check password
        if(!loginAdministrator.getPassword().equals(password)){
            return AjaxRes.failMsg("Wrong Password");
        }

        // check user status
        if (loginAdministrator.getStatus() == 0){
            return  AjaxRes.failMsg("This admin is blocked");
        }

        // set user id into Session
        Long adminId = loginAdministrator.getId();
        request.getSession().setAttribute("Admin",adminId);

        //set userId to BaseContext
//        BaseContext.setCurrentId(adminId);

        loginAdministrator.setOperationTime(LocalDateTime.now());
        this.updateById(loginAdministrator);

        //return success loginUser
        return AjaxRes.success(loginAdministrator);

    }

    @Override
    public AjaxRes<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("Admin");
        return AjaxRes.success("Admin Logout successfully");
    }

    @Override
    public AjaxRes<Administrator> signup(HttpServletRequest request, Administrator administrator) {
        // create unique id
        long adminId = IdWorker.getId();

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9@#$%^&+=!]).{8,}$";
        String password = administrator.getPassword();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches() || password.length() < 8){
            return AjaxRes.failMsg("password does not meet the requirement");
        }

        LambdaQueryWrapper<Administrator> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Administrator::getId, administrator.getId());
        if (count(lqw) > 0){
            return AjaxRes.failMsg("username is already exist");
        }
        // set attributes to admin
        administrator.setPassword(DigestUtils.md5DigestAsHex(administrator.getPassword().getBytes()));
        administrator.setOperationTime(LocalDateTime.now());
        administrator.setStatus(1);

        // save
        this.save(administrator);

        // set session
        request.getSession().setAttribute("Admin", administrator.getId());

        // return
        return AjaxRes.success(administrator);
    }

    @Override
    public AjaxRes<Administrator> updateAdmin(HttpServletRequest request, Administrator administrator) {

//        Long currentId = BaseContext.getCurrentId();
//        currentId = 33L;

        // get currentId
        Long currentId = (Long) request.getSession().getAttribute("Admin");

        // get currentUser
        LambdaQueryWrapper<Administrator> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Administrator::getId, currentId);
        Administrator currentAdministrator = this.getOne(lqw);

        // update username attributes according to frontpage inputs
        currentAdministrator.setUsername(administrator.getUsername());
//        currentAdmin.setGrade(admin.getGrade());

        this.updateById(currentAdministrator);
        return AjaxRes.success(currentAdministrator);
    }

    @Override
    public boolean uniqueUsername(String username) {
        LambdaQueryWrapper<Administrator> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Administrator::getUsername, username);
        int count = this.count(lqw);

        if (count != 0){
            // username already exists
            return false;
        }
        // username not exist
        return true;
    }

    @Override
    public AjaxRes<Administrator> changePassword(HttpServletRequest request, UserPasswordParam param) {
        // get id from BaseContext
//        Long currentId = BaseContext.getCurrentId();
//        currentId = 33L;

        // get currentId
        Long currentId = (Long) request.getSession().getAttribute("Admin");

        // get passwords from param
        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());

        // query user in database
        LambdaQueryWrapper<Administrator> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Administrator::getId, currentId);
        Administrator loginAdministrator = this.getOne(lqw);

        // check old password correct or not
        if (! oldPassword.equals(loginAdministrator.getPassword())){
            return AjaxRes.failMsg("Wrong current password");
        }

        // set new password
        loginAdministrator.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));

        // update and return
        this.updateById(loginAdministrator);
        return AjaxRes.success(loginAdministrator);
    }

    @Override
    public AjaxRes<String> changeAdminStatus(Long adminId,HttpServletRequest request) {
        Administrator administratorTarget = this.getById(adminId);
        Long loginId = (Long) request.getSession().getAttribute("Admin");
        Administrator administratorPresent = this.getById(loginId);
        LambdaUpdateWrapper<Administrator> luw = new LambdaUpdateWrapper<>();
        if(administratorPresent.getGrade() < administratorTarget.getGrade()){
            return AjaxRes.failMsg("Unable to change the administrator status above current account level");
        }else {
            if(administratorTarget.getStatus() == 0){
                luw.eq(Administrator::getId,adminId)
                        .set(Administrator::getStatus,1)
                        .set(Administrator::getOperationTime,LocalDateTime.now());
            }else {
                luw.eq(Administrator::getId,adminId)
                        .set(Administrator::getStatus,0)
                        .set(Administrator::getOperationTime,LocalDateTime.now());
            }
            this.update(luw);
            return AjaxRes.success("change has been made");
        }
    }

    @Override
    public AjaxRes<Page<Administrator>> getAllAdmin(Page page) {
        return AjaxRes.success(this.getBaseMapper().getAllAdmin(page));
    }

    @Override
    public AjaxRes<Page<User>> getAllUser(Page page) {
        return AjaxRes.success(this.getBaseMapper().getAllUser(page));
    }


}
