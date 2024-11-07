package com.trashcode.crash.wallet;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_Wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int WalletId;
    private int amount;

    @OneToOne
    @JoinColumn(name = "user_id")
    user_entity user;
}
