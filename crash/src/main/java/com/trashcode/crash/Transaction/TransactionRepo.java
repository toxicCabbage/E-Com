package com.trashcode.crash.Transaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.wallet.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

      List<Transactions> findByWallet(Wallet wallet);

      // List<Transactions> findAllByTransactionalconditionAndDate(String condtion,
      // Date startDate, Date endDate);

      @Query(value = "from Transactions t where date BETWEEN :startDate And :endDate and transactionalcondition = :tCondition and wallet.user = :user")
      List<Transactions> getTransactionBetweendatawithCondition(@Param("startDate") LocalDate startDate,
                  @Param("endDate") LocalDate endDate, String tCondition, user_entity user);

      @Query("from Transactions u WHERE u.wallet.user.userid = ?1 and u.transactionalcondition = ?2")
      List<Transactions> getSellerIncomeByUserId(int userid, String transactionalCondition);

}
