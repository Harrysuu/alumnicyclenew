package com.elec.alumnicycle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elec.alumnicycle.entity.Comment;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.params.CommentByPostParam;

@Service
public interface CommentService extends IService<Comment> {
    Page<Comment> getCommentsByPost(CommentByPostParam param);
}
