package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.*;
import com.project.cartandordermicroservice.entity.Order;
import com.project.cartandordermicroservice.service.OrderService;
import com.project.cartandordermicroservice.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cartandorder")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderedItemController orderedItemController;

    @Autowired
    EmailService emailService;

    @PostMapping("/order")
    public ResponseDto<Order> order(@RequestHeader("customerId") String customerId,@RequestBody OrderDto orderDto){
        System.out.println(customerId+" customer");
        ResponseDto<Order> responseDto=new ResponseDto<>();
        orderDto.setCustomerId(customerId);
        try {
            Order order=orderService.createOrder(orderDto);
            responseDto.setData(order);
            responseDto.setSuccess(true);
            EmailDto emailDto=new EmailDto();
            emailDto.setSubject("Order placed");
            emailDto.setOrder(order);
            ResponseDto<List<OrderPageDto>> orderPageDtoList=orderedItemController.getOrderedItems(order.getOrderId());
            emailDto.setContent(orderPageDtoList.getData());


            final String uri="http://10.177.68.40:8762/spring-cloud-eureka-client-login/login/";
            HttpHeaders headers=new HttpHeaders();
            headers.add("customerId",customerId);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entityReq=new HttpEntity<>("",headers);
            RestTemplate restTemplate=new RestTemplate();
            ResponseEntity<CustomerDto> customerDto=restTemplate.exchange(uri, HttpMethod.GET, entityReq, CustomerDto.class);
            emailDto.setCustomerName(customerDto.getBody().getName());
            emailDto.setEmail("chirag.modi@coviam.com");
            emailService.produce(emailDto);
        }catch (Exception exception){
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed");
            exception.printStackTrace();
        }
        return responseDto;
    }


    @GetMapping("/order")
    public ResponseDto<List<Order>> getOrders(@RequestHeader("customerId") String customerId){
        ResponseDto<List<Order>> responseDto=new ResponseDto<>();
        try{
            responseDto.setData(orderService.getOrders(customerId));
            responseDto.setSuccess(true);
        }catch (Exception exception){
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed to load orders");
        }
        return responseDto;
    }


}