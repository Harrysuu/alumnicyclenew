package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiModel(value = "Login params")
public class LoginParam {

    @ApiModelProperty(value = "phoneNumber")
    private String phoneNumber;

    @ApiModelProperty(value = "verification code")
    private String code;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "userName")
    private String userName;
}
