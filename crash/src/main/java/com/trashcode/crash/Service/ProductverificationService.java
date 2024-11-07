package com.trashcode.crash.Service;

import java.util.ArrayList;
import java.util.List;

import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.product_entity.tempOrderEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductverificationService {
    @Autowired
    private productRepo Prepo;

    @Autowired
    private OrderRepo oRepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private OrderEntity oEntity;

    public List<OrderEntity> verifiactionService() {

        int sellerId = GCUID.getCurrentUserID();

        List<OrderEntity> productIds = oRepo.findProductidBySellerid(sellerId, conformation.YET_TO_DISPATCH.name());
        return productIds;
    }

    public List<OrderEntity> getPendingProducts() {
        List<OrderEntity> oEntities = oRepo.findPendingProducts(conformation.YET_TO_DISPATCH.name());
        return oEntities;
    }

    public List<OrderEntity> SoldProduct() {

        int sellerId = GCUID.getCurrentUserID();

        List<OrderEntity> productIds = oRepo.findProductidBySellerid(sellerId, conformation.DISPATCH.name());
        System.out.println("sold order values : " + productIds);

        System.out.println("tow@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return productIds;
    }

    public int getOrderPrimaaryKey() {
        int sellerId = GCUID.getCurrentUserID();
        int primaryKey = oRepo.findprimaryKeyBySellerIdtid(sellerId);

        return primaryKey;

    }

}
