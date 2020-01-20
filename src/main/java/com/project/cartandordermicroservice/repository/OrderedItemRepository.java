package com.project.cartandordermicroservice.repository;

import com.project.cartandordermicroservice.entity.OrderedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends CrudRepository<OrderedItem,String> {
}
