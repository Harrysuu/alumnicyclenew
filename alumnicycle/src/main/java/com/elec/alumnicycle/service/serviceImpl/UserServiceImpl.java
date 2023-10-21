package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.LoginParam;
import com.elec.alumnicycle.entity.params.UserParam;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.UserService;
import com.elec.alumnicycle.utils.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public AjaxRes<User> login(HttpServletRequest request, HttpSession session,LoginParam loginparam) {
        if(StringUtils.isNotEmpty(loginparam.getPhoneNumber())){
            Object codeInSession = session.getAttribute(loginparam.getPhoneNumber());
            if(codeInSession.equals(loginparam.getCode())){
                LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
                lqw.eq(User::getPhoneNumber,loginparam.getPhoneNumber());

                //set userId to session
                request.getSession().setAttribute("User",this.getOne(lqw).getId());
                //BaseContext.setCurrentId(this.getOne(lqw).getId());
                return AjaxRes.success(this.getOne(lqw));
            }
            return AjaxRes.failMsg("wrong verification code!");
        }
        // verify password
        String password = loginparam.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info(password);

        // check username in database
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, loginparam.getUsername());
        // as the username is unique, getOne method is applied here
        User loginUser = this.getOne(lqw);

        // if Username is not exist
        if(this.count(lqw) == 0){
            return  AjaxRes.failMsg("User not exist");
//            return AjaxRes.fail(null,"User not exist");
        }

        // check password
        if (!loginUser.getPassword().equals(password)){
            return  AjaxRes.failMsg("Wrong Password");
//            return AjaxRes.fail(null,"Wrong Password");
        }

        // check user status
        if (loginUser.getStatusInformation() == 0){
            return  AjaxRes.failMsg("This user is blocked");
//            return AjaxRes.fail(null,"This user is blocked");
        }

        // set user id into Session
        Long userId = loginUser.getId();
        request.getSession().setAttribute("User",userId);

        //set userId to BaseContext
//        BaseContext.setCurrentId(userId);

        //return success loginUser
        return AjaxRes.success(loginUser);


    }

    @Override
    public AjaxRes<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("User");
        return AjaxRes.success("User Logout successfully");
    }

    @Override
    public AjaxRes<User> signup(HttpServletRequest request, User user) {

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9@#$%^&+=!]).{8,}$";
        String password = user.getPassword();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches() || password.length() < 8){
            return AjaxRes.failMsg("password does not meet the requirement");
        }

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,user.getUsername());
        if (count(lqw) > 0){
            return AjaxRes.failMsg("username is already exist");
        }

        // set attributes to user
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setEditTime(LocalDateTime.now());
        user.setStatusInformation(1);

        // save
        this.save(user);

        // set session
        request.getSession().setAttribute("User",user.getId());

        // return
        return AjaxRes.success(user);
    }

    @Override
    public AjaxRes<Page<User>> getUserByPage(UserParam param) {
        // get page by lwq
        LambdaQueryWrapper<User> lwq = new LambdaQueryWrapper<>();
        lwq.eq(User::getStatusInformation, param.getStatusInformation());
        lwq.orderByDesc(User::getCreateTime);

        // set to pageInfo
        Page<User> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo, lwq);
        return  AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<User> getUserById(Long userId) {
        // get user
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, userId);
        User user = this.getOne(lqw);

        // return user
        return AjaxRes.success(user);
    }

    @Override
    public AjaxRes<User> updateUser(HttpServletRequest request, User user) {

//        Long currentId = BaseContext.getCurrentId();
//        currentId = 99L;
        Long currentId = (Long) request.getSession().getAttribute("User");

        // get currentUser
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, currentId);
        User currentUser = this.getOne(lqw);

        // update 5 attributes according to frontpage inputs
        currentUser.setUsername(user.getUsername());
        currentUser.setCollege(user.getCollege());
        currentUser.setDescription(user.getDescription());
        currentUser.setEmail(user.getEmail());
        currentUser.setEditTime(LocalDateTime.now());
        currentUser.setPicture(user.getPicture());

        this.updateById(currentUser);
        return AjaxRes.success(currentUser);
    }

    @Override
    public boolean uniqueUsername(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        int count = this.count(lqw);

        // username already exists
        return count == 0;
        // username not exist
    }

    @Override
    public AjaxRes<User> addCredit(HttpServletRequest request, double point) {
//        Long currentId = BaseContext.getCurrentId();
//        currentId = 99L;

        Long currentId = (Long) request.getSession().getAttribute("User");

        // get currentUser
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, currentId);
        User currentUser = this.getOne(lqw);

        // add credit point to account
        Double currentCredit = currentUser.getCredit();
        currentUser.setCredit(point+currentCredit);

        // save and return
        this.updateById(currentUser);
        return AjaxRes.success(currentUser);
    }

    @Override
    public AjaxRes<User> changePassword(HttpServletRequest request, UserPasswordParam param) {
        // get id from BaseContext
//        Long currentId = BaseContext.getCurrentId();
//        currentId = 99L;

        Long currentId = (Long) request.getSession().getAttribute("User");

        // get passwords from param
        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());

        // query user in database
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, currentId);
        User loginUser = this.getOne(lqw);

        // check old password correct or not
        if (! oldPassword.equals(loginUser.getPassword())){
            return AjaxRes.failMsg("Wrong current password");
        }

        // set new password
        loginUser.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));

        // update and return
        this.updateById(loginUser);
        return AjaxRes.success(loginUser);

    }

    @Override
    public AjaxRes<String> changeUserStatus(Long userId) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, userId);
        User targetUser = this.getOne(lqw);

        Integer currentStatusInformation = targetUser.getStatusInformation();

        if (currentStatusInformation == 1){
            targetUser.setStatusInformation(0);
        }else if (currentStatusInformation == 0){
            targetUser.setStatusInformation(1);
        }

        this.updateById(targetUser);

        return AjaxRes.success();
    }

    @Override
    public AjaxRes<String> loginByPhone(HttpSession session, String phoneNumber) {

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StringUtils.isNotEmpty(phoneNumber),User::getPhoneNumber,phoneNumber);
        if(this.count(lqw) > 0 && phoneNumber.length() == 11){
            int codeNumber = new Random().nextInt(8999)+1000;
            String code = Integer.toString(codeNumber);
            log.info("verification code : {}",code);
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phoneNumber,code);
            session.setAttribute(phoneNumber,code);
        }else {
            AjaxRes.fail("This phone number does not exist!");
        }
        return AjaxRes.success("Login success");
    }


}
