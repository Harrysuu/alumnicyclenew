package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.Announcement;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.params.LifePostByIdParam;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.mapper.LifePostMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LifePostService extends IService<LifePost> {

    public AjaxRes<Page<LifePost>> getPostByPage(LifePostParam param);

    public AjaxRes<String> addLifePost(HttpServletRequest request, LifePost lifePost);

    public AjaxRes<String>  deleteLifePost(Long id);

    public AjaxRes<LifePost> updateLifePost(LifePost lifePost);

    public AjaxRes<Page<LifePost>> getPostByUserId(LifePostByIdParam param);

    public AjaxRes<LifePost> enrolLifePost(HttpServletRequest request, Long lifePostId);

    public AjaxRes<LifePost> unEnrolLifePost(HttpServletRequest request, Long lifePostId);

    public boolean enrolStatusCheck(HttpServletRequest request, Long lifePostId);

    AjaxRes<LifePost> getPostById(Long id);

    AjaxRes<User> getUser(Long id);

    AjaxRes<List<User>> getAllEnrolledUser(Long id);
}
