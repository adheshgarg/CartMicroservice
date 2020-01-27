package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.entity.OrderedItem;
import com.project.cartandordermicroservice.repository.OrderedItemRepository;
import com.project.cartandordermicroservice.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    @Autowired
    OrderedItemRepository orderedItemRepository;

    @Override
    public OrderedItem addOrderedItem(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }

    @Override
    public List<OrderedItem> orderedItems(String orderId) {
        return orderedItemRepository.findByOrderId(orderId);
    }
}
