package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "shopping cart received class")
public class ShoppingCartParam {
    @ApiModelProperty(value = "category")
    private Integer category;

    @ApiModelProperty(value = "page")
    private Integer page;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;
}
