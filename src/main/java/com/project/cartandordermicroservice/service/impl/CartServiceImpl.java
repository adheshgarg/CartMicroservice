package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.entity.Cart;
import com.project.cartandordermicroservice.repository.CartRepository;
import com.project.cartandordermicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Iterable<Cart> getCart(String customerId) {
        return cartRepository.getCartByCustomer(customerId);
    }

    @Override
    public void putIntoCart(Cart cartItem){
        Cart itemInCart=cartRepository.isProductExistsInCart(cartItem.getCustomerId(),cartItem.getProductId(), cartItem.getMerchantId());
        Cart insertedItem;
        if (itemInCart==null)
            insertedItem=cartRepository.save(cartItem);

    }


    @Override
    public void removeCart(String customerId) {
        cartRepository.removeCartByCustomer(customerId);
    }

    @Override
    public void removeCartItem(String customerId, String productId, String merchantId) {
        cartRepository.removeCartItem(customerId,productId,merchantId);
    }
}
