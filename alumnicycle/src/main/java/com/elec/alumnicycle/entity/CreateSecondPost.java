package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Create_Second_Post entity")
public class CreateSecondPost {

    @ApiModelProperty(value = "Create_Second_Post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "second post id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long secondPostId;

    @ApiModelProperty(value = "user id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
}
