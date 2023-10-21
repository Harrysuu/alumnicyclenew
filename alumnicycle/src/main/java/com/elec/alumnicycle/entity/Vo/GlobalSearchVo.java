package com.elec.alumnicycle.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Global search all elements")
public class GlobalSearchVo {

    @ApiModelProperty(value = "title of the element")
    private String title;

    @ApiModelProperty(value = "id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "picture")
    private String picture;

    @ApiModelProperty(value = "result Type")
    private String type;
}
