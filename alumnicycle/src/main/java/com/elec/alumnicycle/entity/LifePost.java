package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(description = "Life Post entity")
public class LifePost {

    @ApiModelProperty(value = "life post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    private LocalDateTime postTime;

    @ApiModelProperty(value = "activity time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityTime;

    @ApiModelProperty(value = "picture address")
    private String picture;

    @ApiModelProperty(value = "content")
    private String content;

}
