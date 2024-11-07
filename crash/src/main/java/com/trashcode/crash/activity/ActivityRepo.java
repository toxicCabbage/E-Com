package com.trashcode.crash.activity;

import java.util.List;

import com.trashcode.crash.product_entity.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Integer> {

    // Activity findByOrderId(int orderId);

    // @Transactional
    // @Modifying
    // @Query("UPDATE product_entity SET miniDes = :miniDes WHERE productid = :uId")
    // void updateDescription(String miniDes, int uId);

    @Transactional
    @Modifying
    @Query("UPDATE Activity SET Log = :log WHERE order = :order")
    void updateActivity(String log, OrderEntity order);

    List<Activity> findFirst3BySellerIdOrderByIdDesc(int sellerId);

    List<Activity> findFirst3ByOrderByIdDesc();

    // List<ProductStats> findFirst3BySellerIdOrderByOrderCountDesc(int SellerId);

}
