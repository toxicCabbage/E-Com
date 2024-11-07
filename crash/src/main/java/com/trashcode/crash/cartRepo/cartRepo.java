package com.trashcode.crash.cartRepo;

import com.trashcode.crash.product_entity.cartEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;

public interface cartRepo extends JpaRepository<cartEntity, Long> {

    cartEntity findByid(Long i);

    @Query("FROM cartEntity ce WHERE ce.id = ?1")
    cartEntity findBycartId(int id);

    public List<cartEntity> findByUser(user_entity ue);

    long deleteByuser(user_entity user);

    // @Query("FROM cartEntity ce WHERE ce.userId = ?1")
    // cartEntity getByuserID(long userId);

    // // cartEntity findByuserIdAndproductId(long uid, long pid);

    // @Query("from cartEntity where userid =?1 AND productid = ?2")
    // cartEntity findByuserIdAndproductId(long uid, long pid);

    @Query("FROM cartEntity ce WHERE ce.user = ?1 and ce.product = ?2")
    cartEntity findAllcartEntityByuserIdAndproductId(user_entity user, product_entity product);

    // // List <cartEntity> findByuserId(long uid);

    // @Query("SELECT c FROM cartEntity c JOIN c.ProDuct p WHERE c.userId = ?1")
    // List <cartEntity> findAllCartEntites(long id);

    @Query("SELECT c.product.productid FROM cartEntity c WHERE c.user.userid = ?1")
    List<Integer> getAllCartProduct(int userId);

    @Transactional
    @Modifying
    @Query("delete from cartEntity ce where ce.user.userid = ?1 and ce.product.productid = ?2")
    int deleteCartEntityByUserAndProduct(int userId, long productId);

}

// <product_entity, Long>
// private long iid;
// private long userId;
// private long productId;