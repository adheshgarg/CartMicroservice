package com.project.cartandordermicroservice.service;


import com.project.cartandordermicroservice.entity.Cart;

public interface CartService {

    Iterable<Cart> getCart(String customerId);
    void putIntoCart(Cart cartItem);

}
