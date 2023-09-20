package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.CreateLifePost;
import com.elec.alumnicycle.mapper.CreateLifePostMapper;
import com.elec.alumnicycle.service.CreateLifePostService;
import org.springframework.stereotype.Service;

@Service
public class CreateLifePostServiceImpl extends ServiceImpl<CreateLifePostMapper, CreateLifePost> implements CreateLifePostService {
}
