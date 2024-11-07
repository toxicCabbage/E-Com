package com.trashcode.crash.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.product_entity;

@Service
public class ProductService {
    @Autowired
    productRepo pRepo;

    public List<product_entity> getFeaters() {
        return pRepo.getFiveRandom();
    }

}
