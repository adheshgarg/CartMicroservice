package com.project.cartandordermicroservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class DummyCartDto {

    private String customerId;
    private List<DummyCartListDto> cartDtoList;
}
