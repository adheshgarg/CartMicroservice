package com.project.cartandordermicroservice.dto;


import lombok.Data;

@Data
public class DummyCartListDto {

    private String productId;
    private String merchantId;
    private Integer quantityBrought;

}
