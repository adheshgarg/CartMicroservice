package com.project.cartandordermicroservice.service;

import com.project.cartandordermicroservice.entity.OrderedItem;

public interface OrderedItemService {

    OrderedItem addOrderedItem(OrderedItem orderedItem);
    Iterable<OrderedItem> addOrderedItem(Iterable<OrderedItem> orderedItem);


    }
