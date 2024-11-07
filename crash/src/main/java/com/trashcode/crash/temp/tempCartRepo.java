package com.trashcode.crash.temp;

import com.trashcode.crash.product_entity.tempCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tempCartRepo extends JpaRepository<tempCart, Integer> {

}
