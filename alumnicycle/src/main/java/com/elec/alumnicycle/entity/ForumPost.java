package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "forum Post entity")
public class ForumPost {

    @ApiModelProperty(value = "Forum post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "Forum category")
    private String category;

    @ApiModelProperty(value = "Academic title")
    private String title;

    @ApiModelProperty(value = "Academic college")
    private String college;

    @ApiModelProperty(value = "Academic content")
    private String content;

    @ApiModelProperty(value = "create time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postTime;

    @ApiModelProperty(value = "edit time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "picture forum")
    private String picture;

}
