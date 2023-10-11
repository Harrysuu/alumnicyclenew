package com.elec.alumnicycle.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec.alumnicycle.entity.Comment;
import com.elec.alumnicycle.mapper.CommentMapper;
import com.elec.alumnicycle.service.CommentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.params.CommentByPostParam;

@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Page<Comment> getCommentsByPost(CommentByPostParam param) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getForumPostId, param.getForumPostId());
        return this.page(new Page<>(param.getPage(), param.getPageSize()), lqw);
    }
}
