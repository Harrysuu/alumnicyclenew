package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "user received class")
public class UserParam {
    @ApiModelProperty(value = "status")
    private Integer statusInformation;

    @ApiModelProperty(value = "page")
    private Integer page;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;
}
