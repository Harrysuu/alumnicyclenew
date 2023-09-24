package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Admin entity")
public class Admin {
    @ApiModelProperty(value = "Admin id")
    private Long id;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "user password")
    private String password;

    @ApiModelProperty(value = "user status")
    private Integer status;

    @ApiModelProperty(value = "create time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "grade")
    private Integer grade;
}
