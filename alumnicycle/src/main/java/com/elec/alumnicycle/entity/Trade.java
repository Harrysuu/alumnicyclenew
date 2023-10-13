package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Trade entity")
public class Trade {
    @ApiModelProperty(value = "Trade id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "buyer id")
    private Long buyerId;

    @ApiModelProperty(value = "seller id")
    private Long sellerId;

    @ApiModelProperty(value = "commodity id")
    private Long commodityId;

    @ApiModelProperty(value = "commodity name")
    private String commodityName;

    @ApiModelProperty(value = "count of each goods")
    private Integer count;

    @ApiModelProperty(value = "trading status")
    private Integer status;

    @ApiModelProperty(value = "unit price")
    private Double unitPrice;

    @ApiModelProperty(value = "total price")
    private Double totalPrice;


    @ApiModelProperty(value = "generation time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime generationTime;
}