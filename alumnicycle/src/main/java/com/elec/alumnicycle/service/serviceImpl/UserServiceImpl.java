package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

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
}
