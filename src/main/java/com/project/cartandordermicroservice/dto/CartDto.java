package com.project.cartandordermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {

    private String id;
    private String customerId;
    private String productId;
    private String merchantId;
    private Integer quantityBrought;

}
