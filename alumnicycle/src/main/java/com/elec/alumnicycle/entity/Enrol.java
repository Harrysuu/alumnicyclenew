package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Enrol for Life Post entity")
public class Enrol {

    @ApiModelProperty(value = "entity id")
    private Long id;

    @ApiModelProperty(value = "lifePost id")
    private Long lifePostId;

    @ApiModelProperty(value = "user id")
    private Long userId;

}
