package com.project.cartandordermicroservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = OrderedItem.ORDEREDITEM_TABLE_NAME)
public class OrderedItem {

    public static final String ORDEREDITEM_TABLE_NAME = "ORDEREDITEM";

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
//    @ManyToOne
//    @JoinColumn(name = "order_id")
    private String orderId;
    private String merchantId;
    private String productId;
    private Integer productPrice;
    private Integer quantityBrought;
    private Boolean isReviewed;

}
