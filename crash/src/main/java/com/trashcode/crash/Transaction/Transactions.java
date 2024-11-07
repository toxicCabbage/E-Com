package com.trashcode.crash.Transaction;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.trashcode.crash.wallet.Wallet;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private String transactionalcondition;
    private int transactionAmount;
    private String transactionDetail;
    private String imageUrl;

    // @Temporal(TemporalType.DATE)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
