package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Comment entity")
public class Comment {

    @ApiModelProperty(value = "comment id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "user id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty(value = "comment")
    private String comment;

    @ApiModelProperty(value = "forumPost id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long forumPostId;
}
