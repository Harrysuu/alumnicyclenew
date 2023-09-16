package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import org.springframework.stereotype.Service;

public interface LifePostService extends IService<LifePost> {

    public AjaxRes<Page<LifePost>> getPostBypage(LifePostParam param);
}
