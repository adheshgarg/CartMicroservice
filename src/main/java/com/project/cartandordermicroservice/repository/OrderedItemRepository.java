package com.project.cartandordermicroservice.repository;

import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.OrderedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemRepository extends CrudRepository<OrderedItem,String> {

    List<OrderedItem> findByOrderId(String orderId);


}
