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

    /**
     * page search
     * @param param
     * @return
     */
    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category")
    public AjaxRes<Page<LifePost>> getPostByPage(@RequestBody LifePostParam param){
        return lifePostService.getPostBypage(param);
    }

    /***
     *add a new lifePost
     * @param lifePost
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "add a new lifePost")
    public AjaxRes<String> addLifePost(@RequestBody LifePost lifePost){
        return lifePostService.addLifePost(lifePost);
    }

    /***
     *delete a lifePost by id
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public AjaxRes<String> deleteLifePost(Long id){
        return lifePostService.deleteLifePost(id);
    }

    /***
     * update lifepost
     * @param lifePost
     * @return
     */
    @PostMapping("/update")
    public AjaxRes<LifePost> updateLifePost(@RequestBody LifePost lifePost){
        return lifePostService.updateLifePost(lifePost);
    }

    /***
     * getPostbyUserId
     * @param param
     * @return
     */
    @GetMapping("/getbyUserId")
    public AjaxRes<Page<LifePost>> getPostbyUserId(LifePostByIdParam param){
        return lifePostService.getPostbyUserId(param);

    }

    /***
     *enrol a lifePost by userId
     * @param lifePostId
     * @return
     */
    @GetMapping("/enrolById")
    public AjaxRes<LifePost> enrolLifePost(Long lifePostId){

        return lifePostService.enrolLifePost(lifePostId);
    }

    /***
     *Unenrol a lifePost by userId
     * @param lifePostId
     * @return
     */
    @GetMapping("/unenrolById")
    public AjaxRes<LifePost> unEnrolLifePost(Long lifePostId){

        return lifePostService.unEnrolLifePost(lifePostId);
    }

    @GetMapping("/enrolCheck")
    public boolean enrolStatusCheck(Long lifePostId){
        return  lifePostService.enrolStatusCheck(lifePostId);
    }

    
}
