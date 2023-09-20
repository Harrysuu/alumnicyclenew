package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public AjaxRes<User> login(HttpServletRequest request, User user) {
        // verify password

        //get id
        Long userId = user.getId();
        //request.getSession().setAttribute("user",userId);

        //改进拦截器里
        // Long userId = (Long) request.getSession().getAttribute("user");

        //set userId to BaseContext

        BaseContext.setCurrentId(userId);

        Long currentId = BaseContext.getCurrentId();
//        String stringValueId = String.valueOf(currentId);
//        log.info(stringValueId);

        return AjaxRes.success(user);
    }
}
