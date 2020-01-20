package com.project.cartandordermicroservice.controller;


import com.project.cartandordermicroservice.dto.CartDto;
import com.project.cartandordermicroservice.dto.ResponseDto;
import com.project.cartandordermicroservice.entity.Cart;
import com.project.cartandordermicroservice.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getCart/{customerId}")
    public ResponseDto<Cart> getCart(@PathVariable("customerId") String customerId){
        ResponseDto<Cart> responseDto=new ResponseDto<>();
        try {
            Iterable<Cart> cartItems=cartService.getCart(customerId);
            responseDto.setData(cartItems);
            responseDto.setSuccess(true);
            }catch (Exception e) {
                responseDto.setSuccess(false);
                responseDto.setMessage("Couldn't retrive items from your cart");
            }
        return responseDto;

    }


    public void insertIntoCart(Iterable<CartDto> cartDto){
        Cart cart=new Cart();
        for (CartDto cartItem:cartDto) {
            BeanUtils.copyProperties(cartDto,cart);
            cartService.putIntoCart(cart);
        }
    }

}
