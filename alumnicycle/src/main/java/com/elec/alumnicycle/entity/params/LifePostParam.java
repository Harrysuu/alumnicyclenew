package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(value = "life post received class")
public class LifePostParam {

    @ApiModelProperty(value = "category")
    private Integer category;

    @ApiModelProperty(value = "page")
    private Integer page;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;
}
