package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.common.BaseContext;
import com.elec.alumnicycle.entity.CreateLifePost;
import com.elec.alumnicycle.entity.CreateSecondPost;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.params.SecondPostByIdParam;
import com.elec.alumnicycle.entity.params.SecondPostParam;
import com.elec.alumnicycle.mapper.SecondPostMapper;
import com.elec.alumnicycle.service.CreateLifePostService;
import com.elec.alumnicycle.service.CreateSecondPostService;
import com.elec.alumnicycle.service.SecondPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SecondPostServiceImpl extends ServiceImpl<SecondPostMapper, SecondPost> implements SecondPostService {

    @Autowired
    CreateSecondPostService createSecondPostService;


    @Override
    public AjaxRes<Page<SecondPost>> getPostByPage(SecondPostParam param) {
        LambdaQueryWrapper<SecondPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(param.getCategory() != 0,SecondPost::getCategory,param.getCategory())
                .orderByDesc(SecondPost::getCreateTime);
        Page<SecondPost> pageInfo = new Page<>(param.getPage(), param.getPageSize());
        this.page(pageInfo,lqw);
        return AjaxRes.success(pageInfo);
    }
    @Override
    public AjaxRes<Page<SecondPost>> getPostByUserId(SecondPostByIdParam param) {
        return AjaxRes.success(this.getBaseMapper().getSecondPostsByUserId(param.getPage(),param.getUserId()));
    }

    @Override
    public AjaxRes<String> addSecondPost(SecondPost secondPost) {

        //get user id
        Long userId = BaseContext.getCurrentId();

        // for test only
        userId = (long) 1;
        String stringValueId = String.valueOf(userId);
        log.info( "test get user id"+String.valueOf(stringValueId));

        //set post time
        secondPost.setCreateTime(LocalDateTime.now());

        // save to secondPost
        this.save(secondPost);

        //save to link table CreateSecondPost
        CreateSecondPost create = new CreateSecondPost();
        create.setSecondPostId(secondPost.getId());
        create.setUserId(userId);
        createSecondPostService.save(create);

        return AjaxRes.success("New SecondPost is added");
    }

    @Override
    public AjaxRes<String> deleteSecondPost(Long id) {

        //delete link table CreateSecondPost row;
        LambdaQueryWrapper<CreateSecondPost> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CreateSecondPost::getSecondPostId,id);
        createSecondPostService.remove(lqw);

        // delete post by id
        this.removeById(id);

        return AjaxRes.success("The SecondPost is deleted");
    }

    @Override
    public AjaxRes<SecondPost> updateSecondPost(SecondPost secondPost) {
        secondPost.setCreateTime(LocalDateTime.now());

        this.updateById(secondPost);

        return AjaxRes.success(secondPost);
    }
}
