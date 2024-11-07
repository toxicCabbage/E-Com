package com.trashcode.crash.activity;

import java.util.List;

import com.trashcode.crash.ProudctServices.productServices;
import com.trashcode.crash.SellerService.SellerServiceImpal;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServices implements ActivityInterface {

    @Autowired
    private SellerServiceImpal sellerservice;

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private productServices pServices;

    @Autowired
    private getCurrentUserId GCUID;

    @Override
    public void createActivity(product_entity product, user_entity user, OrderEntity order) {
        Activity activity = new Activity();
        int sellerId = sellerservice.getSellerIdByProductId((int) product.getProductid());

        activity.setLog("Order Placed");
        activity.setProduct(product);
        activity.setSellerId(sellerId);
        activity.setUser(user);
        activity.setOrder(order);

        Activity RetActivity = activityRepo.save(activity);

        System.out.println("activity Created successfully");

        System.out.println("Activity : " + RetActivity);

        // return RetActivity;

    }

    @Override
    public void orderPlacedActivity(int productId, int orderId) {

        // String log = "Order Placed";

        // createActivity(productId, orderId, log);

    }

    @Override
    public void orderConformActvity(int orderId) {

        String log = "Order Delivered";
        OrderEntity order = new OrderEntity();
        order.setOrderId(orderId);

        activityRepo.updateActivity(log, order);

        System.out.println("Activity Updated Successfully");

    }

    @Override
    public Activity getActivityByOrderId(int orderId) {

        // return activityRepo.findByOrderId(orderId);
        return null;
    }

    @Override
    public List<Activity> getActivityBySellerId() {

        return activityRepo.findFirst3BySellerIdOrderByIdDesc(GCUID.getCurrentUserID());
    }

    @Override
    public List<Activity> getRecentActivity() {
        List<Activity> activities = activityRepo.findFirst3ByOrderByIdDesc();
        return activities;
    }

}
