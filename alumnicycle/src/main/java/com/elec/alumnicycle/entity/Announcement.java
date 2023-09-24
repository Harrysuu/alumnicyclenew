package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Announcement entity")
public class Announcement {

    @ApiModelProperty(value = "Announcement id")
    private Long id;

    @ApiModelProperty(value = "Announcement title")
    private String title;

    @ApiModelProperty(value = "Announcement content")
    private String content;

    @ApiModelProperty(value = "post time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postTime;

    @ApiModelProperty(value = "Announcement star")
    private Integer star;
}
