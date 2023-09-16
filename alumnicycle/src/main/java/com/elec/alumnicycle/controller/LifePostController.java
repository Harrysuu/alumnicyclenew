package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.LifePost;
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

}
