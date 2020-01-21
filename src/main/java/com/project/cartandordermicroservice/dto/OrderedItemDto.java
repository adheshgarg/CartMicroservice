package com.project.cartandordermicroservice.dto;

import com.project.cartandordermicroservice.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderedItemDto {

    private String id;
    private String orderId;
    private String merchantId;
    private String productId;
    private Integer price;
    private Integer quantity;

}
