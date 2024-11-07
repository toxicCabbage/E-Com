package com.trashcode.crash.product_entity;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_OrdertProduct")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class OrderEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int orderId;
private int sellerId;
private String SellerName;
private String conformation;
private LocalDateTime OrderOn;

    @ManyToOne
    @JoinColumn(name = "productid")
    private product_entity product;

    @ManyToOne
    @JoinColumn(name = "userid")
    private user_entity user;



}


