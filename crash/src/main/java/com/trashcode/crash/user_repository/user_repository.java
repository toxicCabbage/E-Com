package com.trashcode.crash.user_repository;

import javax.transaction.Transactional;

import com.trashcode.crash.auth.Roles;

// import java.util.List;

import com.trashcode.crash.user_entity.user_entity;

import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface user_repository extends JpaRepository<user_entity, Integer> {

    // List <user_entity> findByusername(String username);
    user_entity findByusername(String username);

    user_entity findByuserid(int userID);

    Page<user_entity> findAll(Pageable pageable);

    user_entity findAllByuserid(int userId);

    user_entity findByRoles(Roles role);

    // @Query(value = "SELECT u.password From tbl_user u WHERE u.userid = :uId")

    @Query(value = "SELECT password FROM tbl_user where userid = :uId", nativeQuery = true)
    String getPassword(int uId);

    // @Query(value = "SELECT product_name FROM tbl_product where product_name LIKE
    // %:keyword%",
    // nativeQuery = true)
    // public List<String> getproductName(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE user_entity  SET profilePic = :path WHERE userid = :uId")
    void updateProfilePicture(String path, int uId);

    @Transactional
    @Modifying
    @Query("UPDATE user_entity SET password = :password WHERE userid = :uId")
    void updatePassword(String password, int uId);

    @Transactional
    @Modifying
    @Query("UPDATE user_entity  SET profileBGPic = :path WHERE userid = :uId")
    void updateprofileBGPic(String path, int uId);

    @Query("SELECT ue.username FROM user_entity ue where ue.userid = :uId")
    String finduserNameByuserid(int uId);

    // @Query("SELECT count(u) FROM user_entity u where u.roles = :Role")
    long countByRoles(Roles Role);

}
