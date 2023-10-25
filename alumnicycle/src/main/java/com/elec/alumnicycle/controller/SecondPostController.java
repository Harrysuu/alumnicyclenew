package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ForumPost;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.params.ForumPostByIdParam;
import com.elec.alumnicycle.entity.params.SecondPostByIdParam;
import com.elec.alumnicycle.entity.params.SecondPostParam;
import com.elec.alumnicycle.service.SecondPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/secondPost")
@Slf4j
@Api(tags = "Second post page")
public class SecondPostController {
    @Autowired
    private SecondPostService secondPostService;

    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category")
    public AjaxRes<Page<SecondPost>> getPostByPage(@RequestBody SecondPostParam param){
        return secondPostService.getPostByPage(param);
    }

    @PostMapping ("/getByUserId")
    @ApiOperation(value = "get secondPost by userIds")
    public AjaxRes<Page<SecondPost>> getPostByUserId(@RequestBody SecondPostByIdParam param){
        return secondPostService.getPostByUserId(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new secondPost")
    public AjaxRes<String> addSecondPost(@RequestBody SecondPost secondPost,HttpServletRequest request){
        return secondPostService.addSecondPost(secondPost,request);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete secondPost")
    public AjaxRes<String> deleteSecondPost(Long id){
        return secondPostService.deleteSecondPost(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update secondPost")
    public AjaxRes<SecondPost> updateSecondPost(@RequestBody SecondPost secondPost){
        return secondPostService.updateSecondPost(secondPost);
    }

}

