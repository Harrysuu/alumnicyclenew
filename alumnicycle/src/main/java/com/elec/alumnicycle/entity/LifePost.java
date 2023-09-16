package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "Life Post entity")
public class LifePost {

    @ApiModelProperty(value = "life post id")
    private Long id;

    @ApiModelProperty(value = "life post category")
    private Integer category;

    @ApiModelProperty(value = "life post title")
    private String title;

    @ApiModelProperty(value = "number of people enroll in the activity")
    private Long peopleEnrol;

    @ApiModelProperty(value = "activity address")
    private String address;

    @ApiModelProperty(value = "create time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime;

    @ApiModelProperty(value = "activity time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityTime;

}
