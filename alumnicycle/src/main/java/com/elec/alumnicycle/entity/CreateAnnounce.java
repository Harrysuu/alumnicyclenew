package com.elec.alumnicycle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "crate_announce entity")
public class CreateAnnounce {

    @ApiModelProperty(value = "crate_announce id")
    private Long id;

    @ApiModelProperty(value = "admin id")
    private Long adminId;

    @ApiModelProperty(value = "announce id")
    private Long announcementId;


}
