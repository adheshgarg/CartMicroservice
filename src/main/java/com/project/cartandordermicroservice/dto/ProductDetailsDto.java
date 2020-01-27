package com.project.cartandordermicroservice.dto;


import lombok.Data;

@Data
public class ProductDetailsDto {

    private String productId;
    private String merchantId;
    private double productPrice;

}
