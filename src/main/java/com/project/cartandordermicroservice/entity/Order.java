package com.project.cartandordermicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = Order.ORDER_TABLE_NAME)
public class Order {

    public static final String ORDER_TABLE_NAME = "ORDER";

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderId;
    private String customerId;


    private Date date ;
    private Integer totalPrice=0;
    private String address;

}
