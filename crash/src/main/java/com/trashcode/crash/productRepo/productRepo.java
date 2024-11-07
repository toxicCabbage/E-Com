package com.trashcode.crash.productRepo;

import java.util.List;

import javax.transaction.Transactional;

import com.trashcode.crash.product_entity.product_entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<product_entity, Integer> {

  List<product_entity> findByproductCate(String cate);

  product_entity findByproductid(long id);

  List<product_entity> findByuserid(int userid);

  @Query(value = "FROM  product_entity pe WHERE pe.productCate = ?1")
  Page<product_entity> findALLproduct_entityByproductCate(String cate, Pageable pageable);

  // Page<product_entity> findAllproduct_entity(Pageable pageable);

  Page<product_entity> findALLproduct_entityByuserid(int userId, Pageable pageable);

  Page<product_entity> findAll(Pageable pageable);

  // @Query(value = "FROM product_entity pe WHERE pe.product_name LIKE %:keyword%"
  // )
  // public Page<product_entity> findALLproduct_entityByTerm(String
  // keyword,Pageable pageable);
  Page<product_entity> findALLproduct_entityByproductCateAndProductPriceGreaterThanAndProductPriceLessThan(String cate,
      int min, int max, Pageable pageable);

  @Query(value = "SELECT pe FROM product_entity pe WHERE pe.productName LIKE %:keyword%")
  public Page<product_entity> searchByproductNameLike(String keyword, Pageable pageable);

  Page<product_entity> findByproductNameIsContaining(String keyword, Pageable Pageable);

  @Query(value = "SELECT product_name FROM tbl_product where product_name LIKE %:keyword%")
  public List<String> getproductName(@Param("keyword") String keyword);

  @Query(value = "SELECT userid FROM tbl_product where productid = :pId", nativeQuery = true)
  public int getSellerIdByProductid(int pId);

  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productImgUrl0 = :path WHERE productid = :uId")
  void updateProductPicture1(String path, long uId);

  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productImgUrl1 = :path WHERE productid = :uId")
  void updateProductPicture2(String path, long uId);

  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productImgUrl2 = :path WHERE productid = :uId")
  void updateProductPicture3(String path, long uId);

  // Update Product Name
  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productName = :Name WHERE productid = :uId")
  void updateproductName(String Name, long uId);

  // Update Product Price
  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productPrice = :Price WHERE productid = :uId")
  void updateproductPrice(String Price, long uId);

  // Update Product Category
  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET productCate = :productCate WHERE productid = :uId")
  void updateproductCate(String productCate, long uId);

  // Update Product Description
  @Transactional
  @Modifying
  @Query("UPDATE product_entity  SET miniDes = :miniDes WHERE productid = :uId")
  void updateDescription(String miniDes, long uId);

  @Query(value = "SELECT userid FROM tbl_product where productid = :pId", nativeQuery = true)
  int getuserid(int pId);

  // @Query("SELECT r.id FROM RuleVo r where r.name = :name")
  // List<Long> findIdByName(@Param("name") String name);

  @Query("SELECT pe.userid FROM product_entity pe where pe.productid = :PId")
  int finduseridByproductid(long PId);

  @Transactional
  @Modifying
  @Query("delete from product_entity ce where ce.userid = ?1 and ce.productid = ?2")
  int deleteProductEntityByUserAndProduct(int userId, int productId);

  int countByUserid(int userId);

  List<product_entity> findFirst3ByUseridOrderByStockAsc(int sellerId);
  // List<ProductStats> findFirst3BySellerIdOrderByOrderCountDesc(int SellerId);

  // @Query(value = "SELECT product_name FROM tbl_product where product_name LIKE
  // %:keyword%", nativeQuery = true)
  // public List<String> getproductName(@Param("keyword") String keyword);

  @Query(value = "SELECT * FROM tbl_product LIMIT 5", nativeQuery = true)
  public List<product_entity> getFiveRandom();

}
