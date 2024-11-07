package com.trashcode.crash.product_entity;


import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class tempOrderEntity {

    private int orderNumber;
    private String productImgUrl0;
    private String productName;
    private String productCate;
    private String productPrice;
    private String SellerName;
    private int productid;
    private LocalDateTime OrderOn;
    private String conformation;


    
}










