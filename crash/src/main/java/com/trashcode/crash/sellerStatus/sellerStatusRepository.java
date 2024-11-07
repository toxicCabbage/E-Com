package com.trashcode.crash.sellerStatus;

import java.util.List;

import javax.transaction.Transactional;

import com.trashcode.crash.user_entity.user_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface sellerStatusRepository extends JpaRepository<sellerStats, Integer> {

    sellerStats findBySeller(user_entity seller);

    // @Transactional
    // @Modifying
    // @Query("UPDATE ProductStats SET orderCount = :counter WHERE product =
    // :product")
    // void addorderCount(product_entity product, int counter);

    @Transactional
    @Modifying
    @Query("UPDATE sellerStats SET totalOrders = :totalOrder WHERE seller = :seller")
    void addOrderCount(int totalOrder, user_entity seller);

    List<sellerStats> findFirst3ByOrderByTotalOrdersDesc();

}
