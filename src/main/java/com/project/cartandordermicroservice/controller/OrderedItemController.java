package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.OrderPageDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.OrderedItem;
import com.project.cartandordermicroservice.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cartandorder")
public class OrderedItemController {


    @Autowired
    OrderedItemService orderedItemService;

    @GetMapping("/orderedItem/{id}")
    public ResponseDto<List<OrderPageDto>> getOrderedItems(@PathVariable("id") String orderId){
        ResponseDto<List<OrderPageDto>> responseDto=new ResponseDto<>();
        try {
            List<OrderedItem> orderedItems=orderedItemService.orderedItems(orderId);
            List<OrderPageDto> orderPageDtos=new ArrayList<>();

            for (OrderedItem orderedItem:orderedItems) {
                OrderPageDto orderDto=new OrderPageDto();
                orderDto.setOrderId(orderId);
                orderDto.setProductId(orderedItem.getProductId());
                orderDto.setMerchantId(orderedItem.getMerchantId());
                orderDto.setProductPrice(orderedItem.getProductPrice());
                orderDto.setQuantityBrought(orderedItem.getQuantityBrought());
                orderPageDtos.add(orderDto);
            }

            final String uri="http://10.177.68.40:8762/spring-cloud-eureka-client-product/product/getOrderPageDetails";
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<OrderPageDto>> entityReq=new HttpEntity<>(orderPageDtos,headers);
            RestTemplate restTemplate=new RestTemplate();
            responseDto.setData(restTemplate.postForObject(uri,entityReq,List.class));
            responseDto.setSuccess(true);
        }catch (Exception exception){
            exception.printStackTrace();
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed to load ordered items");
        }
        return responseDto;
    }

}
