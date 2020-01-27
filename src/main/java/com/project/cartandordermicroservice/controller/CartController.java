package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.*;
import com.project.cartandordermicroservice.entity.Cart;
import com.project.cartandordermicroservice.service.CartService;
import org.springframework.beans.BeanUtils;
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
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public ResponseDto<List> getCart(@RequestHeader("customerId") String customerId){
        ResponseDto<List> responseDto=new ResponseDto<>();
        System.out.println("In cart");
        try {
            List<Cart> cartItems= cartService.getCart(customerId);
            List<CartPageDto> cartPageDtos=new ArrayList<>();
            final String url="http://10.177.68.40:8762/spring-cloud-eureka-client-merchant/merchant/productdetails/productPrice";
            for (Cart cart:cartItems) {
                CartPageDto cartPageDto=new CartPageDto();
                cartPageDto.setCustomerId(customerId);
                cartPageDto.setProductId(cart.getProductId());
                cartPageDto.setMerchantId(cart.getMerchantId());
                cartPageDto.setQuantityBrought(cart.getQuantityBrought());

                HttpHeaders headers=new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<CartPageDto> entityReq=new HttpEntity<>(cartPageDto,headers);
                RestTemplate restTemplate=new RestTemplate();
                ProductDetailsDto createdProductDetail=restTemplate.postForObject(url,entityReq,ProductDetailsDto.class);
                cartPageDto.setProductPrice(createdProductDetail.getProductPrice());
                cartPageDtos.add(cartPageDto);
            }

            final String uri="http://10.177.68.40:8762/spring-cloud-eureka-client-product/product/getCartPageDetails";
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List> entityReq=new HttpEntity<>(cartPageDtos,headers);
            RestTemplate restTemplate=new RestTemplate();
            responseDto.setData(restTemplate.postForObject(uri,entityReq,List.class));
            responseDto.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            responseDto.setSuccess(false);
            responseDto.setMessage("Couldn't retrieve items from your cart");
        }
        return responseDto;

    }

    @PostMapping("/cart")
    public ResponseDto<String> insertIntoCart(@RequestHeader("customerId") String customerId,@RequestBody DummyCartDto cartDto){
        ResponseDto<String> responseDto=new ResponseDto<>();
        cartDto.setCustomerId(customerId);
        try {
            List<DummyCartListDto> cartDtoList=cartDto.getCartDtoList();
            for (DummyCartListDto cartItem:cartDtoList) {
                Cart cart=new Cart();
                BeanUtils.copyProperties(cartItem,cart);
                cart.setCustomerId(cartDto.getCustomerId());
                responseDto.setSuccess(cartService.putIntoCart(cart));      //returns false if it is already in cart
                responseDto.setMessage("Successfully added to cart");
            }
        }catch (Exception e){
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed to update");
            e.printStackTrace();
        }
        return responseDto;
    }

    @DeleteMapping("/cart")
    ResponseDto<Cart> deleteCartItem(@RequestHeader("customerId") String customerId,@RequestBody CartDto cartDto){
        ResponseDto<Cart> responseDto=new ResponseDto<>();
        cartDto.setCustomerId(customerId);
        try{
            cartService.removeCartItem(cartDto);
            responseDto.setSuccess(true);
        }catch (Exception exception){
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

}
