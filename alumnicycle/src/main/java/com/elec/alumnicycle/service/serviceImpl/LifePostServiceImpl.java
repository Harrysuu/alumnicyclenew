package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.CreateLifePost;
import com.elec.alumnicycle.entity.Enrol;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.mapper.EnrolMapper;
import com.elec.alumnicycle.mapper.LifePostMapper;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.CreateLifePostService;
import com.elec.alumnicycle.service.EnrolService;
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

    @Autowired
    EnrolService enrolService;

    @Override
    public AjaxRes<Page<LifePost>> getPostByPage(LifePostParam param) {
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
    public AjaxRes<Page<LifePost>> getPostbyUserId(LifePostByIdParam param) {

        List<LifePost> lifePosts = lifePostMapper.getLifePostsByUserId(param.getUserId());

        Page<LifePost> page = new Page<>(param.getPage(), param.getPageSize());
        page.setRecords(lifePosts);

        return AjaxRes.success(page);

    }

    @Override
    public AjaxRes<LifePost> enrolLifePost(Long lifePostId) {
        // get current userID
//        Long currentUserId = BaseContext.getCurrentId();
        Long currentUserId = 99L;

        //get lifepost
        LambdaQueryWrapper<LifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LifePost::getId,lifePostId);
        LifePost lifePost = this.getOne(lqw);

        // increase num of people enrol by 1
        Long peopleEnrol = lifePost.getPeopleEnrol();
        lifePost.setPeopleEnrol(peopleEnrol+1);
        // save to LifePostService
        this.updateById(lifePost);

        // add data into link table "enrol"
        Enrol enrol = new Enrol();
        enrol.setUserId(currentUserId);
        enrol.setLifePostId(lifePostId);
        enrolService.save(enrol);

        return AjaxRes.success(lifePost);
    }

    @Override
    public AjaxRes<LifePost> unEnrolLifePost(Long lifePostId) {
        // get current userID
//        Long currentUserId = BaseContext.getCurrentId();
        Long currentUserId = 99L;

        //get lifepost
        LambdaQueryWrapper<LifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LifePost::getId,lifePostId);
        LifePost lifePost = this.getOne(lqw);

        // decrease num of people enrol by 1
        Long peopleEnrol = lifePost.getPeopleEnrol();
        lifePost.setPeopleEnrol(peopleEnrol-1);
        // save to LifePostService
        this.updateById(lifePost);

        // delete data in link table "enrol"
        LambdaQueryWrapper<Enrol> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(Enrol::getLifePostId,lifePostId).eq(Enrol::getUserId,currentUserId);
        enrolService.remove(lqw2);

        return AjaxRes.success(lifePost);
    }

    @Override
    public boolean enrolStatusCheck(Long lifePostId) {
        // get current userID
//        Long currentUserId = BaseContext.getCurrentId();
        Long currentUserId = 99L;

        // sql
        LambdaQueryWrapper<Enrol> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Enrol::getUserId, currentUserId);
        lqw.eq(Enrol::getLifePostId, lifePostId);
        int count = enrolService.count(lqw);
        log.info(String.valueOf(count));

        // return status
        if (count > 0){
            return true;
        }
        else{
            return false;
        }

    }


}


