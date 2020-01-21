package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.OrderDto;
import com.project.cartandordermicroservice.dto.OrderedItemDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Order;
import com.project.cartandordermicroservice.entity.OrderedItem;
import com.project.cartandordermicroservice.service.CartService;
import com.project.cartandordermicroservice.service.OrderService;
import com.project.cartandordermicroservice.service.OrderedItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseDto<Order> order(@RequestBody OrderDto orderDto){
        ResponseDto<Order> responseDto=new ResponseDto<>();
        try {
            Order order=orderService.createOrder(orderDto);
            responseDto.setData(order);
            responseDto.setSuccess(true);
        }catch (Exception exception){
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed");
        }
        return responseDto;
    }

}
