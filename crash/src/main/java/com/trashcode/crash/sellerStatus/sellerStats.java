package com.trashcode.crash.sellerStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_Seller_Stats")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component

public class sellerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int totalOrders;

    @OneToOne
    private user_entity seller;
}
