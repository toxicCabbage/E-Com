package com.trashcode.crash.SellerService;

import java.util.List;

import javax.xml.bind.PrintConversionEvent;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.product_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpal implements SellerService {
    @Autowired
    private productRepo Prepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Override
    public void DeleteProductByProudctId(int proudctId) {
        int deletedItems = Prepo.deleteProductEntityByUserAndProduct(GCUID.getCurrentUserID(), proudctId);
        System.out.println("total DELeted Items : " + deletedItems);
    }

    @Override
    public List<product_entity> SellerProduct(int userId) {
        List<product_entity> pe = Prepo.findByuserid(userId);

        return pe;
    }

    @Override
    public int getSellerIdByProductId(int productId) {
        int sellerId = Prepo.getuserid(productId);
        System.out.println("SELLER ID -------------------- " + sellerId);
        return sellerId;
    }

    @Override
    public int sellerProfit(int productPrice) {

        return (productPrice * 80) / 100;
    }

}
