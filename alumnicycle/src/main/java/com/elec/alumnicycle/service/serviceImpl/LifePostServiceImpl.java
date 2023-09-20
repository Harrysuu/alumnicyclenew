package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.CreateLifePost;
import com.elec.alumnicycle.mapper.LifePostMapper;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.CreateLifePostService;
import com.elec.alumnicycle.service.LifePostService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class LifePostServiceImpl extends ServiceImpl<LifePostMapper, LifePost> implements LifePostService {


    @Autowired
    LifePostMapper lifePostMapper;

    @Autowired
    CreateLifePostService createLifePostService;




    @Override
    public AjaxRes<Page<LifePost>> getPostBypage(LifePostParam param) {
        LambdaQueryWrapper<LifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(param.getCategory() != 0,LifePost::getCategory,param.getCategory())
                .orderByDesc(LifePost::getPostTime);
        Page<LifePost> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }

    @Override
    public AjaxRes<String> addLifePost(LifePost lifePost) {

        //get user id
        Long userId = BaseContext.getCurrentId();

        // for test only
        userId = (long) 1;
        String stringValueId = String.valueOf(userId);
        log.info( "test get user id"+String.valueOf(stringValueId));

        //set post time
        lifePost.setPostTime(LocalDateTime.now());

        // save to lifePost
        this.save(lifePost);

        //save to link table CreateLifePost
        CreateLifePost create = new CreateLifePost();
        create.setLifePostId(lifePost.getId());
        create.setUserId(userId);
        createLifePostService.save(create);

        return AjaxRes.success("New LifePost is added");
    }

    @Override
    public AjaxRes<String> deleteLifePost(Long id) {
        //get user id
        Long userId = BaseContext.getCurrentId();

        // for test only
        userId = (long) 1;
        String stringValueId = String.valueOf(userId);
        log.info(stringValueId);

        //delete link table CreateForumPost row;
        LambdaQueryWrapper<CreateLifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CreateLifePost::getLifePostId,id);
        createLifePostService.remove(lqw);

        // delete post by id
         this.removeById(id);

        return AjaxRes.success("The LifePost is deleted");
    }

    @Override
    public AjaxRes<LifePost> updateLifePost(LifePost lifePost) {
        lifePost.setPostTime(LocalDateTime.now());

        this.updateById(lifePost);

        return AjaxRes.success(lifePost);
    }

    @Override
    public AjaxRes<Page<LifePost>> getPostbyUserId(Long userId) {

        List<LifePost> lifePosts = lifePostMapper.getLifePostsByUserId(userId);

        Page<LifePost> page = new Page<>(1, 10);
        page.setRecords(lifePosts);

        return AjaxRes.success(page);

    }



}
