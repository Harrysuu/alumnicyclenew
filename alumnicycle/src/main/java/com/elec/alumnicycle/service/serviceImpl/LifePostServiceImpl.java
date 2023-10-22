package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.*;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.mapper.EnrolMapper;
import com.elec.alumnicycle.mapper.LifePostMapper;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.CreateLifePostService;
import com.elec.alumnicycle.service.EnrolService;
import com.elec.alumnicycle.service.LifePostService;
import com.elec.alumnicycle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class LifePostServiceImpl extends ServiceImpl<LifePostMapper, LifePost> implements LifePostService {

    @Autowired
    CreateLifePostService createLifePostService;

    @Autowired
    EnrolService enrolService;

    @Autowired
    UserService userService;

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
    public AjaxRes<String> addLifePost(HttpServletRequest request, LifePost lifePost) {

        //get user id
//        Long userId = BaseContext.getCurrentId();
        Long userId = (Long) request.getSession().getAttribute("User");

        // for test only
//        userId = 99L;
//        String stringValueId = String.valueOf(userId);
//        log.info( "test get user id"+String.valueOf(stringValueId));

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
    public AjaxRes<Page<LifePost>> getPostByUserId(LifePostByIdParam param) {
        return AjaxRes.success(this.getBaseMapper().getLifePostsByUserId(param.getPage(),param.getUserId()));
    }

    @Override
    public AjaxRes<LifePost> enrolLifePost(HttpServletRequest request, Long lifePostId) {

//        Long currentUserId = BaseContext.getCurrentId();
//        Long currentUserId = 99L;

        // get current userID
        Long currentUserId = (Long) request.getSession().getAttribute("User");

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
    public AjaxRes<LifePost> unEnrolLifePost(HttpServletRequest request, Long lifePostId) {
        // get current userID
//        Long currentUserId = BaseContext.getCurrentId();
//        Long currentUserId = 99L;

        // get current userID
        Long currentUserId = (Long) request.getSession().getAttribute("User");

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
    public boolean enrolStatusCheck(HttpServletRequest request, Long lifePostId) {
        // get current userID
//        Long currentUserId = BaseContext.getCurrentId();
//        Long currentUserId = 99L;

        // get current userID
        Long currentUserId = (Long) request.getSession().getAttribute("User");

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

    @Override
    public AjaxRes<LifePost> getPostById(Long id) {
        LambdaQueryWrapper<LifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LifePost::getId, id);

        LifePost lifePost = this.getOne(lqw);
        return AjaxRes.success(lifePost);
    }

    @Override
    public AjaxRes<User> getUser(Long id) {
        LambdaQueryWrapper<CreateLifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CreateLifePost::getLifePostId, id);
        CreateLifePost createLifePost = createLifePostService.getOne(lqw);

        Long userId = createLifePost.getUserId();
        return userService.getUserById(userId);

    }

    @Override
    public AjaxRes<List<User>> getAllEnrolledUser(Long id) {
        LambdaQueryWrapper<Enrol> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Enrol::getLifePostId,id);

        List<Enrol> enrolList = enrolService.list(lqw);
        List<User> users = new ArrayList<>();

        for (Enrol enrol : enrolList) {
            Long userId = enrol.getUserId();
            User user = userService.getById(userId);
            if (user != null) {
                users.add(user);
            }
        }

        return AjaxRes.success(users);

    }

    @Override
    public AjaxRes<Page<LifePost>> getAllLifePost(Page page) {
        return AjaxRes.success(this.getBaseMapper().getAllLifePost(page));
    }


}


