package com.elec.alumnicycle.entity.params;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "forum post get by ID received class")
public class ForumPostByIdParam {
    @ApiModelProperty(value = "userId")
    private Long userId;

    @ApiModelProperty(value = "page",required = true)
    private Page page;

}