package com.project.cartandordermicroservice.service;


import com.project.cartandordermicroservice.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getCart(String customerId);
    void putIntoCart(Cart cartItem);
    void removeCartItem(String customerId,String productId, String merchantId);
    void removeCart(String customerId);
}
