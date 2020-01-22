package com.project.cartandordermicroservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = Order.ORDER_TABLE_NAME)
public class Order {

    public static final String ORDER_TABLE_NAME = "ORDERS";

    @Id
    @Column(name = "order_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String orderId;
    private String customerId;
    private Date date;
    private Integer totalPrice=0;
    private String address;

}
