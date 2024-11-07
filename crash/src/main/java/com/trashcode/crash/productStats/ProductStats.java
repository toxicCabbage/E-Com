package com.trashcode.crash.productStats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.trashcode.crash.product_entity.product_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_productStats")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class ProductStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sellerId;
    private int viewCount;
    private int orderCount;

    @OneToOne
    private product_entity product;

}
