package com.trashcode.crash.product_entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_recentProduct")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class recentProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "productid")
    private product_entity product;

    @ManyToOne
    @JoinColumn(name = "userid")
    private user_entity user;

}


