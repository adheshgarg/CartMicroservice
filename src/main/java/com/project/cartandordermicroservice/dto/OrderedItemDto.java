package com.project.cartandordermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderedItemDto {

    private String merchantId;
    private String productId;
    private Integer productPrice;
    private Integer quantityBrought;

}
