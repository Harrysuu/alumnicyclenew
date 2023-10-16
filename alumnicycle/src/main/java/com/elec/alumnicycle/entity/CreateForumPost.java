package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Create_Forum_Post entity")
public class CreateForumPost {

    @ApiModelProperty(value = "Create_Forum_Post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long postId;

    @ApiModelProperty(value = "user id")
    private Long userId;

}
