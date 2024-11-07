package com.trashcode.crash.product_entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tbl_cart")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class cartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerName;

    private int quantity;
  
    @ManyToOne
    @JoinColumn(name = "productid")
    private product_entity product;

    @ManyToOne
    @JoinColumn(name = "userid")
    private user_entity user;
  
 
}
