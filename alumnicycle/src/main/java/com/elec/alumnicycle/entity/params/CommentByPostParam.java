package com.elec.alumnicycle.entity.params;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "comment by post")

public class CommentByPostParam {
    private Long forumPostId;
    private Long userId;
    private int page;
    private int pageSize;
}
