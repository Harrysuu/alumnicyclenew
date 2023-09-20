package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.mapper.LifePostMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LifePostService extends IService<LifePost> {

    public AjaxRes<Page<LifePost>> getPostBypage(LifePostParam param);

    public AjaxRes<String> addLifePost(LifePost lifePost);

    public AjaxRes<String>  deleteLifePost(Long id);

    public AjaxRes<LifePost> updateLifePost(LifePost lifePost);

    public AjaxRes<Page<LifePost>> getPostbyUserId(Long userId);

//    List<LifePost> getLifePostsByUserId(Long userId);

}
