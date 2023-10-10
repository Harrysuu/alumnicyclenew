package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.*;
import com.elec.alumnicycle.entity.params.ForumPostByIdParam;
import com.elec.alumnicycle.entity.params.ForumPostParam;
import com.elec.alumnicycle.mapper.ForumPostMapper;
import com.elec.alumnicycle.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements ForumPostService {

    @Autowired
    ForumPostMapper forumPostMapper;

    @Autowired
    CreateForumPostService createForumPostService;

    @Override
    public AjaxRes<Page<ForumPost>> getPostByPage(ForumPostParam param) {
        LambdaQueryWrapper<ForumPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(param.getCategory() != 0,ForumPost::getCategory,param.getCategory())
                .orderByDesc(ForumPost::getPostTime);
        Page<ForumPost> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<String> addForumPost(ForumPost forumPost) {
        //get user id
        Long userId = BaseContext.getCurrentId();

        // for test only
        userId = (long) 1;
        String stringValueId = String.valueOf(userId);
        log.info( "test get user id"+String.valueOf(stringValueId));

        //set post time
        forumPost.setPostTime(LocalDateTime.now());

        // save to lifePost
        this.save(forumPost);

        //save to link table CreateLifePost
        CreateForumPost create = new CreateForumPost();
        create.setPostId(forumPost.getId());
        create.setUserId(userId);
        createForumPostService.save(create);

        return AjaxRes.success("New ForumPost is added");
    }

    @Override
    public AjaxRes<String> deleteForumPost(Long id) {
        //delete link table CreateForumPost row;
        LambdaQueryWrapper<CreateForumPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CreateForumPost::getPostId,id);
        createForumPostService.remove(lqw);

        // delete post by id
        this.removeById(id);

        return AjaxRes.success("The ForumPost is deleted");
    }

    @Override
    public AjaxRes<ForumPost> updateForumPost(ForumPost forumPost) {
        return null;
    }

    @Override
    public AjaxRes<Page<ForumPost>> getPostByUserId(ForumPostByIdParam param) {
        return AjaxRes.success(this.getBaseMapper().getForumPostsByUserId(param.getPage(),param.getUserId()));
    }
}
