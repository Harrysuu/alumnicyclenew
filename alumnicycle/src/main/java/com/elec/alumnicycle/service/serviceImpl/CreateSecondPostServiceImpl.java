package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.CreateSecondPost;
import com.elec.alumnicycle.mapper.CreateSecondPostMapper;
import com.elec.alumnicycle.service.CreateSecondPostService;
import org.springframework.stereotype.Service;

@Service
public class CreateSecondPostServiceImpl extends ServiceImpl<CreateSecondPostMapper, CreateSecondPost> implements CreateSecondPostService {
}
