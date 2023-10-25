package com.elec.alumnicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Create_Shopping_Cart entity")
public class CreateShoppingCart {
    @ApiModelProperty(value = "Create_Shopping_Cart id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "shopping cart id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long shoppingCartId;

    @ApiModelProperty(value = "user id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
}
