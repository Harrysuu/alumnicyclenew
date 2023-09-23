package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.LifePostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lifePost")
@Slf4j
@Api(tags = "生活帖子页面")
public class LifePostController {

    @Autowired
    private LifePostService lifePostService;

    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category")
    public AjaxRes<Page<LifePost>> getPostByPage(@RequestBody LifePostParam param){
        return lifePostService.getPostBypage(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new lifePost")
    public AjaxRes<String> addLifePost(@RequestBody LifePost lifePost){
        return lifePostService.addLifePost(lifePost);
    }

    @GetMapping("/delete")
    public AjaxRes<String> deleteLifePost(Long id){
        return lifePostService.deleteLifePost(id);
    }

    @PostMapping("/update")
    public AjaxRes<LifePost> updateLifePost(@RequestBody LifePost lifePost){
        return lifePostService.updateLifePost(lifePost);
    }

    @GetMapping("/getbyUserId")
    public AjaxRes<Page<LifePost>> getPostbyUserId(LifePostByIdParam param){
        return lifePostService.getPostbyUserId(param);
    }

    @GetMapping("/enrolById")
    public AjaxRes<LifePost> enrolLifePost(Long lifePostId){
        return lifePostService.enrolLifePost(lifePostId);
    }

    @GetMapping("/unenrolById")
    public AjaxRes<LifePost> unEnrolLifePost(Long lifePostId){
        return lifePostService.unEnrolLifePost(lifePostId);
    }

    @GetMapping("/enrolCheck")
    public boolean enrolStatusCheck(Long lifePostId){
        return  lifePostService.enrolStatusCheck(lifePostId);
    }

}
