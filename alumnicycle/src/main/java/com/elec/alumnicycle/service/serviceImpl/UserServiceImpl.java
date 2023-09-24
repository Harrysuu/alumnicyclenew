package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.UserParam;
import com.elec.alumnicycle.entity.params.UserPasswordParam;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public AjaxRes<User> login(HttpServletRequest request, User user) {
        // verify password
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info(password);

        // check username in database
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        // as the username is unique, getOne method is applied here
        User loginUser = this.getOne(lqw);

        // if Username is not exist
        if(loginUser == null){
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
        request.getSession().setAttribute("user",userId);

        //set userId to BaseContext
        BaseContext.setCurrentId(userId);

        //return success loginUser
        return AjaxRes.success(loginUser);

//        改进拦截器里
//        Long userId = (Long) request.getSession().getAttribute("user");
//        Long currentUserId = BaseContext.getCurrentId();
//        String stringValueId = String.valueOf(currentUserId);
//        log.info(stringValueId);

    }

    @Override
    public AjaxRes<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("User");
        return AjaxRes.success("Logout successfully");
    }

    @Override
    public AjaxRes<String> signup(HttpServletRequest request, User user) {
        //creat a unique Id
        long userId = IdWorker.getId();

        // set attributes to user
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setId(userId);
        user.setCreateTime(LocalDateTime.now());
        user.setEditTime(LocalDateTime.now());
        user.setStatusInformation(1);

        // set session
        request.getSession().setAttribute("user",user.getId());

        // save and return
        this.save(user);
        return AjaxRes.success("Sign up successfully");
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
    public AjaxRes<User> updateUser(User user) {

        Long currentId = BaseContext.getCurrentId();
        currentId = 99L;

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

        this.updateById(currentUser);
        return AjaxRes.success(currentUser);
    }

    @Override
    public boolean uniqueUsername(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        int count = this.count(lqw);

        if (count != 0){
            // username already exists
            return false;
        }
        // username not exist
        return true;
    }

    @Override
    public AjaxRes<User> addCredit(double point) {
        Long currentId = BaseContext.getCurrentId();
        currentId = 99L;

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
    public AjaxRes<User> changePassword(UserPasswordParam param) {
        // get id from BaseContext
        Long currentId = BaseContext.getCurrentId();
        currentId = 99L;

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


}
