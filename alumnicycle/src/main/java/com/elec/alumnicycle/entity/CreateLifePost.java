package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Create_Life_Post entity")
public class CreateLifePost {
    @ApiModelProperty(value = "Create_Life_Post id")
    private Long id;

    @ApiModelProperty(value = "life post id")
    private Long lifePostId;

    @ApiModelProperty(value = "user id")
    private Long userId;
}
