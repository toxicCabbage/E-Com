package com.trashcode.crash.product_entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_product")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class product_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productid;
    private String productName;
    private int productPrice;
    private String productCate;
    private String miniDes;
    private String productImgUrl0;
    private String productImgUrl1;
    private String productImgUrl2;
    private int userid;
    private int stock;

}
