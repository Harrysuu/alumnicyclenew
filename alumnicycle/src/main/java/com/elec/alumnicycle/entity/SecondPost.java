package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Second Post entity")
public class SecondPost {

    @ApiModelProperty(value = "second post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "poster id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long posterId;

    @ApiModelProperty(value = "Second post category")
    private Integer category;

    @ApiModelProperty(value = "picture")
    private String picture;

    @ApiModelProperty(value = "commodity name")
    private String commodityName;

    @ApiModelProperty(value = "price")
    private Double price;

    @ApiModelProperty(value = "count")
    private Integer count;

    @ApiModelProperty(value = "newness")
    private String newness;

    @ApiModelProperty(value = "create time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "edit time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

}
