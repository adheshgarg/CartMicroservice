package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.dto.OrderDto;
import com.project.cartandordermicroservice.dto.OrderedItemDto;
import com.project.cartandordermicroservice.entity.Order;
import com.project.cartandordermicroservice.entity.OrderedItem;
import com.project.cartandordermicroservice.repository.OrderRepository;
import com.project.cartandordermicroservice.service.CartService;
import com.project.cartandordermicroservice.service.OrderService;
import com.project.cartandordermicroservice.service.OrderedItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedItemService orderedItemService;

    @Autowired
    CartService cartService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order createOrder(OrderDto orderDto) {
        Order order=new Order();
        Iterable<OrderedItemDto> orderedItemDtos=orderDto.getOrderedItemDto();
        BeanUtils.copyProperties(orderDto,order, String.valueOf(orderedItemDtos));
        Order createdOrder=orderRepository.save(order);         //new order

        final String uri="http://localhost:8080/productdetails/updateStock";
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Iterable<OrderedItemDto>> entityReq=new HttpEntity<>(orderedItemDtos,headers);
        RestTemplate restTemplate=new RestTemplate();
        boolean result=restTemplate.postForObject(uri,entityReq,boolean.class,orderDto);
        if(result) {
            int totalPrice = 0;
            for (OrderedItemDto orderedItemDto : orderedItemDtos) {
                OrderedItem orderedItem = new OrderedItem();
                BeanUtils.copyProperties(orderedItemDto, orderedItem);
                orderedItem.setOrderId(createdOrder.getOrderId());
                totalPrice += orderedItem.getPrice() * orderedItem.getQuantity();
                orderedItemService.addOrderedItem(orderedItem);
                cartService.removeCartItem(orderDto.getCustomerId(), orderedItem.getProductId(), orderedItem.getMerchantId());
            }
            order.setTotalPrice(totalPrice);
        }
        return order;
    }

}
