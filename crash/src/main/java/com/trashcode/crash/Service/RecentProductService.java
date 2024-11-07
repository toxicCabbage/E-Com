package com.trashcode.crash.Service;

import com.trashcode.crash.productRepo.recentProductRepo;
import com.trashcode.crash.product_entity.recentProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecentProductService {

    @Autowired
    private recentProductRepo RPR;

    @Autowired
    private recentProduct rProduct;

    public boolean RecentProductManager(int proudctId ,int CurrentUserId){

        // rProduct.setProductId(proudctId);
        // rProduct.setUserId(CurrentUserId);
        RPR.save(rProduct);

        return false;
    }
    
}
