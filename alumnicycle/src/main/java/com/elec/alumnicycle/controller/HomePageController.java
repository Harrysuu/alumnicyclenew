package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Slf4j
@Api(tags = "Home Page ")
public class HomePageController {

//    @PostMapping("/announcementPage")
//    @ApiOperation(value = "get announcements")
//    public AjaxRes<Page<LifePost>> getPostByPage(@RequestBody LifePostParam param){
//        return lifePostService.getPostBypage(param);
//    }

}
