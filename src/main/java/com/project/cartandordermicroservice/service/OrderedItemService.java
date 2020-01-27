package com.project.cartandordermicroservice.service;

import com.project.cartandordermicroservice.entity.OrderedItem;

import java.util.List;

public interface OrderedItemService {

    OrderedItem addOrderedItem(OrderedItem orderedItem);
    List<OrderedItem> orderedItems(String orderId);
}
