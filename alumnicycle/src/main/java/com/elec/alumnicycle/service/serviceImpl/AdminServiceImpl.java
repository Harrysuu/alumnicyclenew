package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.Admin;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.mapper.AdminMapper;
import com.elec.alumnicycle.mapper.UserMapper;
import com.elec.alumnicycle.service.AdminService;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
