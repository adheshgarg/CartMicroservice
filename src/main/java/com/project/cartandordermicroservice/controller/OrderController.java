package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.EmailDto;
import com.project.cartandordermicroservice.dto.OrderDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Order;
import com.project.cartandordermicroservice.service.OrderService;
import com.project.cartandordermicroservice.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    EmailService emailService;

    @PostMapping("/")
    public ResponseDto<Order> order(@RequestBody OrderDto orderDto){
        ResponseDto<Order> responseDto=new ResponseDto<>();
        try {
            Order order=orderService.createOrder(orderDto);
            responseDto.setData(order);
            responseDto.setSuccess(true);
            EmailDto emailDto=new EmailDto();
            emailDto.setSubject("Order placed");
            emailDto.setContent(String.valueOf(order));
            emailDto.setEmail("chirag.modi@coviam.com");
            emailService.produce(emailDto);
        }catch (Exception exception){
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed");
        }
        return responseDto;
    }

}
