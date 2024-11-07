package com.trashcode.crash.ProudctServices;

import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.product_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productServices implements productserviceInterface {

    @Autowired
    private productRepo pRepo;

    @Override
    public product_entity getProudctByProductId(int productId) {
        return pRepo.findByproductid(productId);
    }

}
