package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "trade received class")
public class TradeParam {
    @ApiModelProperty(value = "count of each goods")
    private Integer count;

    @ApiModelProperty(value = "page")
    private Integer page;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;
}
