package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.CartDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Cart;
import com.project.cartandordermicroservice.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseDto<Cart> getCart(@PathVariable("customerId") String customerId){
        ResponseDto<Cart> responseDto=new ResponseDto<>();
        try {
            List<Cart> cartItems=cartService.getCart(customerId);
            responseDto.setData((Cart) cartItems);
            responseDto.setSuccess(true);
            }catch (Exception e) {
                responseDto.setSuccess(false);
                responseDto.setMessage("Couldn't retrieve items from your cart");
            }
        return responseDto;

    }

    @PostMapping("/")
    public void insertIntoCart(@RequestBody List<CartDto> cartDtoList){
        Cart cart=new Cart();
        for (CartDto cartItem:cartDtoList) {
            BeanUtils.copyProperties(cartItem,cart);
            cartService.putIntoCart(cart);
        }
    }

    @DeleteMapping("/")
    ResponseDto<Cart> deleteCartItem(@RequestBody String customerId, String productId, String merchantId){
        ResponseDto<Cart> responseDto=new ResponseDto<>();
        try{
            cartService.removeCartItem(customerId,productId,merchantId);
            responseDto.setSuccess(true);
        }catch (Exception exception){
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

}
