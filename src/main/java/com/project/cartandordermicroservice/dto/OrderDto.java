package com.project.cartandordermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrderDto {

    private String orderId;
    private String customerId;
    private Date date;
    private Integer totalPrice;
    private String address;
    private Iterable<OrderedItemDto> orderedItemDto;

}
