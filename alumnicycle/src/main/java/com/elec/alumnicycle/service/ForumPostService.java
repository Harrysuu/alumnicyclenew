package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ForumPost;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.ForumPostByIdParam;
import com.elec.alumnicycle.entity.params.ForumPostParam;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.mapper.ForumPostMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ForumPostService extends IService<ForumPost> {

    public AjaxRes<Page<ForumPost>> getPostByPage(ForumPostParam param);

    public AjaxRes<String> addForumPost(ForumPost forumPost);

    public AjaxRes<String>  deleteForumPost(Long id);

    public AjaxRes<ForumPost> updateForumPost(ForumPost forumPost);

    public AjaxRes<Page<ForumPost>> getPostbyUserId(ForumPostByIdParam param);
}
