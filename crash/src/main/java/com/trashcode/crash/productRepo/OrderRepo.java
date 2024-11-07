package com.trashcode.crash.productRepo;

import java.util.List;

import javax.transaction.Transactional;

import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

    // List <Integer> findProductIdBySellerId(int sellerId);

    @Query("FROM OrderEntity pe where pe.sellerId = :PId and pe.conformation = :flag")
    List<OrderEntity> findProductidBySellerid(int PId, String flag);

    @Query("FROM OrderEntity pe where pe.conformation = :flag")
    List<OrderEntity> findPendingProducts(String flag);

    @Query("Select count(*) from OrderEntity pe where pe.sellerId = :PId and pe.conformation = :flage")
    long countbyFlage(int PId, String flag);

    List<OrderEntity> findByUser(user_entity user);

    // @Transactional
    // @Modifying
    // @Query("UPDATE user_entity SET profileBGPic = :path WHERE userid = :uId")
    // void updateprofileBGPic(String path, int uId);

    // @Query("FROM cartEntity ce WHERE ce.userId = ?1 and ce.productId = ?2")
    // cartEntity findAllcartEntityByuserIdAndproductId(long uid, long pid);

    @Query("SELECT pe.orderId FROM OrderEntity pe where pe.sellerId = :PId")
    int findprimaryKeyBySellerIdtid(int PId);

    @Query("SELECT COUNT(u) FROM OrderEntity u WHERE u.sellerId = ?1 and u.conformation = ?2")
    int countOrderByUserId(int sellerId, String conformation);

    @Query("SELECT COUNT(u) FROM OrderEntity u WHERE u.sellerId = ?1 and u.conformation = ?2")
    int countRequestedProductByUserId(int sellerId, String conformation);

    @Query("SELECT COUNT(u) FROM OrderEntity u WHERE u.conformation = ?1")
    int countRequestedProduct(String conformation);

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity SET conformation = :value WHERE orderId = :pId")
    void conformProductRequest(String value, int pId);

    // List <OrderEntity> findByuserId(int userId);

    @Query("SELECT pe.sellerId FROM OrderEntity pe where pe.orderId = :PId")
    int finduseridByOrderid(int PId);

    @Query("SELECT pe.product.productid FROM OrderEntity pe where pe.orderId = :PId")
    int findProductidByOrderid(int PId);

    @Query("SELECT pe.user FROM OrderEntity pe where pe.orderId = :PId")
    user_entity findUserdByOrderid(int PId);

    @Query("SELECT pe.sellerId FROM OrderEntity pe where pe.orderId = :PId")
    int findSellerIdByOrderId(int PId);

    List<OrderEntity> findLast20BySellerIdAndConformationOrderByOrderIdDesc(int sellerId, String conformation);

    // List<OrderEntity> findLast

    long countByConformation(String conformation);

}