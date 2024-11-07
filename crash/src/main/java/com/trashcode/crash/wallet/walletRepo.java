package com.trashcode.crash.wallet;

import javax.transaction.Transactional;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface walletRepo extends JpaRepository<Wallet, Integer> {

    Wallet findByUser(user_entity usesr);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet  SET amount = :amount WHERE user = :user")
    void updateBalance(int amount, user_entity user);

    @Query(value = "SELECT amount FROM tbl_wallet where user_id = :user", nativeQuery = true)
    Integer getCurrentBlance(int user);
}
