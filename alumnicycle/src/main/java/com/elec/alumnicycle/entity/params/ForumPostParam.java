package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "academic post received class")
public class ForumPostParam {

    @ApiModelProperty(value = "category")
    private Integer category;

    @ApiModelProperty(value = "college")
    private Integer college;

    @ApiModelProperty(value = "page")
    private Integer page;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize;

}
