package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.dto.CartDto;
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
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderedItemService orderedItemService;

    @Autowired
    CartService cartService;


    @Override
    public List<Order> getOrders(String customerId) {
        return orderRepository.findByCustomerIdOrderByDateDesc(customerId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order createOrder(OrderDto orderDto) {

        Order order=new Order();
        List<OrderedItemDto> orderedItemDtos=orderDto.getOrderedItemDto();
        BeanUtils.copyProperties(orderDto,order, String.valueOf(orderedItemDtos));
        order.setDate(new Date());
        int totalPrice = 0;
        final String uri="http://10.177.68.40:8762/spring-cloud-eureka-client-merchant/merchant/productdetails/stockUpdate";
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<OrderedItemDto>> entityReq=new HttpEntity<>(orderedItemDtos,headers);
        RestTemplate restTemplate=new RestTemplate();
        boolean result=restTemplate.postForObject(uri,entityReq,boolean.class);
        Order createdOrder=null;        //new order
        if(result) {
            createdOrder=orderRepository.save(order);
            for (OrderedItemDto orderedItemDto : orderedItemDtos) {
                OrderedItem orderedItem = new OrderedItem();
                BeanUtils.copyProperties(orderedItemDto, orderedItem);
                orderedItem.setProductPrice(orderedItemDto.getProductPrice());
                orderedItem.setQuantityBrought(orderedItemDto.getQuantityBrought());
                orderedItem.setOrderId(createdOrder.getOrderId());
                orderedItem.setIsReviewed(false);
                totalPrice += orderedItem.getProductPrice() * orderedItem.getQuantityBrought();
                orderedItemService.addOrderedItem(orderedItem);
                CartDto cartDto=new CartDto();
                cartDto.setCustomerId(orderDto.getCustomerId());
                cartDto.setProductId(orderedItem.getProductId());
                cartDto.setMerchantId(orderedItem.getMerchantId());
                cartService.removeCartItem(cartDto);
            }
            createdOrder.setTotalPrice(totalPrice);
        }
        return orderRepository.save(createdOrder);
    }

}
