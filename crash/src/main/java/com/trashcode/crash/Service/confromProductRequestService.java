package com.trashcode.crash.Service;

import com.trashcode.crash.activity.ActivityServices;
import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.productStats.ProductStatsServices;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.sellerStatus.sellerStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class confromProductRequestService {

    @Autowired
    private OrderRepo oRepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    ProductStatsServices pServices;

    @Autowired
    private ActivityServices activityServices;

    @Autowired
    private sellerStatusService sService;

    public boolean confromProductRequest(int pId) {

        System.out.println("CONFORM OREDER REQUESR SERVICE :");
        oRepo.conformProductRequest(conformation.DISPATCH.name(), pId);

        // updating Activites
        activityServices.orderConformActvity(pId);

        pServices.addOrderCount(pId);

        sService.addOrderCount(pId);

        // if(oRepo.conformProductRequest(true, pId)){
        // System.out.println("PRoduct Confrom Successfull");
        // return true;
        // }
        // else{
        // System.out.println("failed to confrom product resquest");
        // }

        return true;
    }

}
