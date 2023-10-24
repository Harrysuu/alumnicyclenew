package com.elec.alumnicycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.*;
import com.elec.alumnicycle.entity.params.ForumPostByIdParam;
import com.elec.alumnicycle.entity.params.ForumPostParam;
import com.elec.alumnicycle.entity.params.CommentByPostParam;
import com.elec.alumnicycle.service.CommentService;
import com.elec.alumnicycle.service.ForumPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/forumPost")
@Slf4j
@Api(tags = "Forum post page")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/page")
    @ApiOperation(value = "get posts according to page and category and college")
    public AjaxRes<Page<ForumPost>> getPostByPage(@RequestBody ForumPostParam param) {
        return forumPostService.getPostByPage(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new forumPost")
    public AjaxRes<String> addForumPost(HttpServletRequest request, @RequestBody ForumPost forumPost) {return forumPostService.addForumPost(request, forumPost);}

    @GetMapping("/delete")
    @ApiOperation(value = "delete forumPost")
    public AjaxRes<String> deleteForumPost(Long id){
        return forumPostService.deleteForumPost(id);
    }

    @PostMapping ("/getByUserId")
    @ApiOperation(value = "get forumPost by userIds")
    public AjaxRes<Page<ForumPost>> getPostByUserId(@RequestBody ForumPostByIdParam param){
        return forumPostService.getPostByUserId(param);
    }

    @GetMapping("/addComment")
    @ApiOperation(value = "add comment")
    public AjaxRes<ForumPost> addComment(HttpServletRequest request, Long forumPostId, String comment, Long userId){
        return forumPostService.addComment(request, forumPostId, comment, userId);
    }

    @PostMapping("/getCommentsByPost")
    @ApiOperation(value = "get paginated comments by post id")
    public AjaxRes<Page<Comment>> getCommentsByPost(@RequestBody CommentByPostParam param) {
        return AjaxRes.success(commentService.getCommentsByPost(param));
    }


    @GetMapping("/getPostById")
    @ApiOperation(value = "get by PostID")
    public AjaxRes<ForumPost> getPostById(Long id){
        return forumPostService.getPostById(id);
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "get user who creates the post")
    public AjaxRes<User> getUser(Long id){
        return forumPostService.getUser(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update forumPost")
    public AjaxRes<ForumPost> updateForumPost(@RequestBody ForumPost forumPost){
        return forumPostService.updateForumPost(forumPost);
    }

}
