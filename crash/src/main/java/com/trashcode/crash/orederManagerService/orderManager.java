package com.trashcode.crash.orederManagerService;

import java.time.LocalDateTime;
import java.util.List;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.Transaction.transactionService;
import com.trashcode.crash.activity.ActivityServices;
import com.trashcode.crash.cartRepo.cartRepo;
import com.trashcode.crash.cartServices.cartManager;
import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.cartEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderManager {

    @Autowired
    private OrderRepo oRepo;

    @Autowired
    private user_repository uRepo;

    @Autowired
    private productRepo Prepo;

    @Autowired
    private cartRepo Crepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private cartManager cManager;

    @Autowired
    private transactionService tService;

    @Autowired
    private ActivityServices activityServices;

    public void addOrder(int productId) {

        System.out.println("----------------------------  ADDING ORDER --------------------------");

        user_entity user = new user_entity();
        product_entity product = new product_entity();
        OrderEntity item = new OrderEntity();

        user = uRepo.findByuserid(GCUID.getCurrentUserID());
        product = Prepo.findByproductid(productId);

        tService.buyProduct(product);

        LocalDateTime now = LocalDateTime.now();
        // get Seller id from product table
        int SellerId = Prepo.finduseridByproductid(productId);
        String SellerName = uRepo.finduserNameByuserid(SellerId);

        item.setConformation(conformation.YET_TO_DISPATCH.name());
        item.setOrderOn(now);
        item.setProduct(product);
        item.setUser(user);
        item.setSellerName(SellerName);
        item.setSellerId(SellerId);

        OrderEntity ReturnOrder = oRepo.save(item);

        System.out.println("RETURN ORDER : " + ReturnOrder);
        activityServices.createActivity(product, user, ReturnOrder);

    }

    public void checkoutAllItems() {
        System.out.println("/n checkout all items");
        List<Integer> items = Crepo.getAllCartProduct(GCUID.getCurrentUserID());

        for (Integer i : items) {
            System.out.println("product id : " + i);
            addOrder(i);
            cManager.DeleteCartItemByUserAndProduct(GCUID.getCurrentUserID(), i);
        }

    }

    public List<OrderEntity> getAllOrderByUser() {
        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());

        List<OrderEntity> orderItems = oRepo.findByUser(user);
        System.out.println("all USER order : " + orderItems);

        return orderItems;

    }

    // for(recentProduct rp : recentItems){
    // System.out.println("recent PRoducts : " + rp.getProductId());
    // }

}
