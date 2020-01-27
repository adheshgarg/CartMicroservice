package com.project.cartandordermicroservice.dto;

import com.project.cartandordermicroservice.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class EmailDto {
    private String email;
    private String subject;
    private Order order;
    private List<OrderPageDto> content;
    private String customerName;
}