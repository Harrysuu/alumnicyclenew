package com.elec.alumnicycle.entity.params;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec.alumnicycle.entity.Announcement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Announcement get by ID received class")
public class AnnouncementByIdParam {

    @ApiModelProperty(value = "adminId")
    private Long adminId;

    @ApiModelProperty(value = "page",required = true)
    private Page page;

}
