package com.elec.alumnicycle.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User change password Param")
public class UserPasswordParam {
    @ApiModelProperty(value = "old password")
    private String oldPassword;

    @ApiModelProperty(value = "new password")
    private String newPassword;

}
