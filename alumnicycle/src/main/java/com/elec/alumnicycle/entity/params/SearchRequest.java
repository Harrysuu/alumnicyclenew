package com.elec.alumnicycle.entity.params;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Search param")
public class SearchRequest {

    @ApiModelProperty(value = "search title string")
    private String keyword;
}
