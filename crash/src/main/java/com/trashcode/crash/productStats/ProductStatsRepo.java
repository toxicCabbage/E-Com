package com.trashcode.crash.productStats;

import java.util.List;

import javax.transaction.Transactional;

import com.trashcode.crash.product_entity.product_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatsRepo extends JpaRepository<ProductStats, Integer> {

    // @Transactional
    // @Modifying
    // @Query("UPDATE product_entity SET miniDes = :miniDes WHERE productid = :uId")
    // void updateDescription(String miniDes, int uId);

    @Transactional
    @Modifying
    @Query("UPDATE ProductStats SET orderCount = :counter WHERE product = :product")
    void addorderCount(product_entity product, int counter);

    @Transactional
    @Modifying
    @Query("UPDATE ProductStats SET viewCount = :counter WHERE product = :product")
    void addviewCount(product_entity product, int counter);

    ProductStats findByProduct(product_entity product);

    // List<product_entity> findBottom5ByUserOrderByUserDesc(user_entity user);

    // @Query("SELECT s.product FROM ProductStats s WHERE s.SellerId =
    // :SellerIdORDER BY OrderCount DESC LIMIT 3")
    // List<product_entity> getTopOrderedProducts(@Param("SellerId") int SellerId);

    // List<ProductStats> findFirst5BySellerIdOrderByOrderCountDesc(int SellerId);
    // List<recentProduct> findFirst5ByUserOrderByIdDesc(user_entity user);

    List<ProductStats> findFirst3BySellerIdOrderByOrderCountDesc(int SellerId);

    List<ProductStats> findFirst3ByOrderByOrderCountDesc();

    // ProductStats findBysellerId(int sellerId);

}
