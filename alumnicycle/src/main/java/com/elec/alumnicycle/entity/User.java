package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(description = "User entity")
public class User {

    @ApiModelProperty(value = "User id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "user password")
    private String password;

    @ApiModelProperty(value = "user credit point")
    private Double credit;

    @ApiModelProperty(value = "user belonged college")
    private String college;

    @ApiModelProperty(value = "user status")
    private Integer statusInformation;

    @ApiModelProperty(value = "user self description")
    private String description;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "create time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "edit time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "picture address")
    private String picture;
}
