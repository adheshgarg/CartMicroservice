package com.project.cartandordermicroservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = Cart.CART_TABLE_NAME)
public class Cart {

    public static final String CART_TABLE_NAME = "CART";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String customerId;
    private String productId;
    private String merchantId;
    private Integer productQuantity;

}
