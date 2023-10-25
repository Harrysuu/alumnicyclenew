package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.params.SecondPostByIdParam;
import com.elec.alumnicycle.entity.params.SecondPostParam;

import javax.servlet.http.HttpServletRequest;

public interface SecondPostService extends IService<SecondPost> {
    public AjaxRes<Page<SecondPost>> getPostByPage(SecondPostParam param);

    public AjaxRes<Page<SecondPost>> getPostByUserId(SecondPostByIdParam param);

    public AjaxRes<String> addSecondPost(SecondPost secondPost,HttpServletRequest request);

    public AjaxRes<String>  deleteSecondPost(Long id);

    public AjaxRes<SecondPost> updateSecondPost(SecondPost secondPost);

    public AjaxRes<Page<SecondPost>> getAllSecondPost(Page page);
}
