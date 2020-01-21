package com.project.cartandordermicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = OrderedItem.ORDEREDITEM_TABLE_NAME)
public class OrderedItem {

    public static final String ORDEREDITEM_TABLE_NAME = "ORDEREDITEM";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
 //   @ManyToOne
 //   @JoinColumn(name = "order_id")
    private String orderId;
    private String merchantId;
    private String productId;
    private Integer price;
    private Integer quantity;

}
