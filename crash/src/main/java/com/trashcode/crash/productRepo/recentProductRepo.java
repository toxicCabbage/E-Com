package com.trashcode.crash.productRepo;

import java.util.List;

import com.trashcode.crash.product_entity.cartEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.product_entity.recentProduct;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface recentProductRepo extends JpaRepository<recentProduct, Long> {

    // List<recentProduct> findTop4ByuserId(int userId);
    // // List<recentProduct> findTop4ByuserId(int userId);
    // // List<recentProduct> findTopByOrderByUserIdAsc(int userId);

    // List<recentProduct> findTop5ByUserIdOrderByUserIdAsc(int userId);

    @Query("FROM recentProduct ce WHERE ce.user = ?1 and ce.product = ?2")
    recentProduct findAllRecentEntityByuserIdAndproductId(user_entity user, product_entity product);

    List<recentProduct> findTop5ByUserOrderByUserAsc(user_entity user);

    List<recentProduct> findBottom5ByUserOrderByUserDesc(user_entity user);

    List<recentProduct> findFirst5ByUserOrderByIdDesc(user_entity user);

}
