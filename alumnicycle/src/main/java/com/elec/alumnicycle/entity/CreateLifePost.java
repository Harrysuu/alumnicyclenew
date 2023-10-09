package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Create_Life_Post entity")
public class CreateLifePost {
    @ApiModelProperty(value = "Create_Life_Post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "life post id")
    private Long lifePostId;

    @ApiModelProperty(value = "user id")
    private Long userId;
}
