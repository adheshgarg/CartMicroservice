package com.project.cartandordermicroservice.service;

import com.project.cartandordermicroservice.dto.OrderDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDto orderDto);
    List<Order> getOrders(String customerId);

}
