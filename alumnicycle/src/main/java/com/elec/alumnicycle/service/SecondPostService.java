package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.params.SecondPostByIdParam;
import com.elec.alumnicycle.entity.params.SecondPostParam;

public interface SecondPostService extends IService<SecondPost> {
    public AjaxRes<Page<SecondPost>> getPostByPage(SecondPostParam param);

    public AjaxRes<Page<SecondPost>> getPostByUserId(SecondPostByIdParam param);

    public AjaxRes<String> addSecondPost(SecondPost secondPost);

    public AjaxRes<String>  deleteSecondPost(Long id);

    public AjaxRes<SecondPost> updateSecondPost(SecondPost secondPost);
}
