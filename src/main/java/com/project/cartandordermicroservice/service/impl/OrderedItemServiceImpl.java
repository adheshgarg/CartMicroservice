package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.entity.OrderedItem;
import com.project.cartandordermicroservice.repository.OrderedItemRepository;
import com.project.cartandordermicroservice.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    @Autowired
    OrderedItemRepository orderedItemRepository;

    @Override
    public OrderedItem addOrderedItem(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }

    @Override
    public Iterable<OrderedItem> addOrderedItem(Iterable<OrderedItem> orderedItem) {
        return orderedItemRepository.saveAll(orderedItem);
    }
}
