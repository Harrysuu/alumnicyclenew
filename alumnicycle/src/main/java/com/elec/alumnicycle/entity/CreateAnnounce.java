package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "crate_announce entity")
public class CreateAnnounce {

    @ApiModelProperty(value = "crate_announce id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "admin id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long adminId;

    @ApiModelProperty(value = "announce id")
    private Long announcementId;


}
