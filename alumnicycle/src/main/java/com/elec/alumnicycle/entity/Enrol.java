package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Enrol for Life Post entity")
public class Enrol {

    @ApiModelProperty(value = "entity id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "lifePost id")
    private Long lifePostId;

    @ApiModelProperty(value = "user id")
    private Long userId;

}
