package com.project.cartandordermicroservice.repository;

import com.project.cartandordermicroservice.entity.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,String> {

    @Query(
            value = "select * from Cart where customer_id=:customerId",
            nativeQuery = true
    )
    List<Cart> getCartByCustomer(@Param("customerId") String customerId);

    @Modifying
    @Query(
            value = "delete from cart where customer_id=:customerId",
            nativeQuery = true
    )
    void removeCartByCustomer(@Param("customerId") String customerId);


    @Modifying
    @Query(
            value = "delete from cart where customer_id=:customerId and product_id=:productId and merchant_id=:merchantId",
            nativeQuery = true
    )
    void removeCartItem(@Param("customerId") String customerId,@Param("productId") String productId,@Param("merchantId") String merchantId);



    @Query(
            value = "select * from Cart where customer_id=:customerId and product_id=:productId and merchant_id=:merchantId",
            nativeQuery = true
    )
    Cart isProductExistsInCart(@Param("customerId") String customerId, @Param("productId") String productId, @Param("merchantId") String merchantId);
}
