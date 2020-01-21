package com.project.cartandordermicroservice.service;

import com.project.cartandordermicroservice.dto.OrderDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Order;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

}
