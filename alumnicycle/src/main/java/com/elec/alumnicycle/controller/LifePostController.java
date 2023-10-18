package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.LifePostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifePost")
@Slf4j
@Api(tags = "Life post page")
public class LifePostController {

    @Autowired
    private LifePostService lifePostService;

    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category")
    public AjaxRes<Page<LifePost>> getPostByPage(@RequestBody LifePostParam param){
        return lifePostService.getPostByPage(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new lifePost")
    public AjaxRes<String> addLifePost(@RequestBody LifePost lifePost){
        return lifePostService.addLifePost(lifePost);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete lifePost")
    public AjaxRes<String> deleteLifePost(Long id){
        return lifePostService.deleteLifePost(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update lifePost")
    public AjaxRes<LifePost> updateLifePost(@RequestBody LifePost lifePost){
        return lifePostService.updateLifePost(lifePost);
    }

    @PostMapping ("/getByUserId")
    @ApiOperation(value = "get lifePost by userIds")
    public AjaxRes<Page<LifePost>> getPostByUserId(@RequestBody LifePostByIdParam param){
        return lifePostService.getPostByUserId(param);
    }

    @GetMapping("/enrolById")
    @ApiOperation(value = "enrol lifePost")
    public AjaxRes<LifePost> enrolLifePost(Long lifePostId){
        return lifePostService.enrolLifePost(lifePostId);
    }

    @GetMapping("/cancelEnrolById")
    @ApiOperation(value = "Cancel enrol lifePost")
    public AjaxRes<LifePost> unEnrolLifePost(Long lifePostId){
        return lifePostService.unEnrolLifePost(lifePostId);
    }

    @GetMapping("/enrolCheck")
    @ApiOperation(value = "check enrolment of lifePost")
    public boolean enrolStatusCheck(Long lifePostId){
        return  lifePostService.enrolStatusCheck(lifePostId);
    }

    @GetMapping("/getPostById")
    @ApiOperation(value = "get by PostID")
    public AjaxRes<LifePost> getPostById(Long id){
        return lifePostService.getPostById(id);
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "get user who creates the post")
    public AjaxRes<User> getUser(Long id){
        return lifePostService.getUser(id);
    }

    @GetMapping("/getAllEnrolledUser")
    @ApiOperation(value = "get all enrolled users")
    public AjaxRes<List<User>> getAllEnrolledUser(Long id){
        return lifePostService.getAllEnrolledUser(id);
    }

}
