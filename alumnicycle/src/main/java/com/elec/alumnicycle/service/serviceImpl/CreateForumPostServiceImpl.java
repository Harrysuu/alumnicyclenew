package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.CreateForumPost;
import com.elec.alumnicycle.mapper.CreateForumPostMapper;
import com.elec.alumnicycle.service.CreateForumPostService;
import org.springframework.stereotype.Service;

@Service
public class CreateForumPostServiceImpl extends ServiceImpl<CreateForumPostMapper, CreateForumPost> implements CreateForumPostService {
}
