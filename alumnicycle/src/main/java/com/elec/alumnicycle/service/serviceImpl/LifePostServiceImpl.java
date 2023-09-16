package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.mapper.LifePostMapper;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.params.LifePostParam;
import com.elec.alumnicycle.service.LifePostService;
import org.springframework.stereotype.Service;

@Service
public class LifePostServiceImpl extends ServiceImpl<LifePostMapper, LifePost> implements LifePostService {

    @Override
    public AjaxRes<Page<LifePost>> getPostBypage(LifePostParam param) {
        LambdaQueryWrapper<LifePost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(param.getCategory() != 0,LifePost::getCategory,param.getCategory())
                .orderByDesc(LifePost::getPostTime);
        Page<LifePost> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }
}
