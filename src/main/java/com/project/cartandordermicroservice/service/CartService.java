package com.project.cartandordermicroservice.service;


import com.project.cartandordermicroservice.dto.CartDto;
import com.project.cartandordermicroservice.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getCart(String customerId);
    boolean putIntoCart(Cart cartItem);
    void removeCartItem(CartDto cartDto);
    void removeCart(String customerId);
}
