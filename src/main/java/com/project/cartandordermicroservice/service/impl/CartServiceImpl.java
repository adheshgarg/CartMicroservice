package com.project.cartandordermicroservice.service.impl;

import com.project.cartandordermicroservice.dto.CartDto;
import com.project.cartandordermicroservice.entity.Cart;
import com.project.cartandordermicroservice.repository.CartRepository;
import com.project.cartandordermicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> getCart(String customerId) {
        return cartRepository.getCartByCustomer(customerId);
    }

    @Override
    public boolean putIntoCart(Cart cartItem){
        boolean flag=false;
        Cart itemInCart=cartRepository.isProductExistsInCart(cartItem.getCustomerId(),cartItem.getProductId(), cartItem.getMerchantId());
        if (itemInCart==null) {
            Cart insertedItem = cartRepository.save(cartItem);
            flag=true;
        }
        return flag;
    }


    @Transactional
    @Override
    public void removeCart(String customerId) {
        cartRepository.removeCartByCustomer(customerId);
    }

    @Transactional
    @Override
    public void removeCartItem(CartDto cartDto) {
        cartRepository.removeCartItem(cartDto.getCustomerId(),cartDto.getProductId(),cartDto.getMerchantId());
    }


}
