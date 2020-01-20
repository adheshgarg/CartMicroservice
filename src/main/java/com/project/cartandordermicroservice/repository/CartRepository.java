package com.project.cartandordermicroservice.repository;

import com.project.cartandordermicroservice.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartRepository extends CrudRepository<Cart,String> {

    @Query(
            value = "select * from Cart where customer_id=?1",
            nativeQuery = true
    )
    Collection<Cart> getCartByCustomer(String customerId);



    @Query(
            value = "select * from Cart where customer_id=?1 and product_id=?2 and merchant_id=?3",
            nativeQuery = true
    )
    Cart isProductExistsInCart(String customerId,String productId, String merchantId);

}
