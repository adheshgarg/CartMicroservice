package com.project.cartandordermicroservice.repository;

import com.project.cartandordermicroservice.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<Order,String> {



    List<Order> findByCustomerIdOrderByDateDesc(String customerId);

}
