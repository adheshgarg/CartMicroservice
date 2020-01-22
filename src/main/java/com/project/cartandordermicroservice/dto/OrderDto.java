package com.project.cartandordermicroservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private String customerId;
    private String address;
    private List<OrderedItemDto> orderedItemDto;

}
