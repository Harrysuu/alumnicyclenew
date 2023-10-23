package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elec.alumnicycle.common.AjaxRes;
import com.elec.alumnicycle.entity.ForumPost;
import com.elec.alumnicycle.entity.LifePost;
import com.elec.alumnicycle.entity.SecondPost;
import com.elec.alumnicycle.entity.User;
import com.elec.alumnicycle.entity.vo.GlobalSearchVo;
import com.elec.alumnicycle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    UserService userService;

    @Autowired
    LifePostService lifePostService;

    @Autowired
    ForumPostService forumPostService;

    @Autowired
    SecondPostService secondPostService;

    @Override
    public AjaxRes<List<GlobalSearchVo>> search(String keyword) {

        List<GlobalSearchVo> globalSearchVos = new ArrayList<>();

        LambdaQueryWrapper<User> userLqw = new LambdaQueryWrapper<>();
        userLqw.like(User::getUsername,keyword);
        List<User> users = userService.list(userLqw);
        if(users.size() > 0){
            for (User user : users) {
                GlobalSearchVo vo = new GlobalSearchVo();
                vo.setId(user.getId());
                vo.setTitle(user.getUsername());
                vo.setPicture(user.getPicture());
                vo.setType("user");
                globalSearchVos.add(vo);
            }
        }

        LambdaQueryWrapper<LifePost> lifeLqw = new LambdaQueryWrapper<>();
        lifeLqw.like(LifePost::getTitle,keyword)
                .orderByDesc(LifePost::getPostTime);
        List<LifePost> lifePosts = lifePostService.list(lifeLqw);
        if(lifePosts.size() > 0){
            for (LifePost lifePost : lifePosts) {
                GlobalSearchVo vo = new GlobalSearchVo();
                vo.setTitle(lifePost.getTitle());
                vo.setId(lifePost.getId());
                vo.setPicture(lifePost.getPicture());
                vo.setType("lifePost");
                globalSearchVos.add(vo);
            }
        }

        LambdaQueryWrapper<ForumPost> forumLqw = new LambdaQueryWrapper<>();
        forumLqw.like(ForumPost::getTitle,keyword)
                .orderByDesc(ForumPost::getPostTime);
        List<ForumPost> forumPosts = forumPostService.list(forumLqw);
        if (forumPosts.size() > 0){
            for (ForumPost forumPost : forumPosts) {
                GlobalSearchVo vo = new GlobalSearchVo();
                vo.setId(forumPost.getId());
                vo.setTitle(forumPost.getTitle());
                vo.setPicture(forumPost.getPicture());
                vo.setType("forumPost");
                globalSearchVos.add(vo);
            }
        }

        LambdaQueryWrapper<SecondPost> secondLqw = new LambdaQueryWrapper<>();
        secondLqw.like(SecondPost::getCommodityName,keyword)
                .orderByDesc(SecondPost::getCreateTime);
        List<SecondPost> secondPosts = secondPostService.list(secondLqw);
        if(secondPosts.size() > 0){
            for (SecondPost secondPost : secondPosts) {
                GlobalSearchVo vo = new GlobalSearchVo();
                vo.setId(secondPost.getId());
                vo.setPicture(secondPost.getPicture());
                vo.setTitle(secondPost.getCommodityName());
                vo.setType("secondPost");
                globalSearchVos.add(vo);
            }
        }

        return AjaxRes.success(globalSearchVos);
    }
}
