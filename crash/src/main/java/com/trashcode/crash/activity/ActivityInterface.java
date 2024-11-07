package com.trashcode.crash.activity;

import java.util.List;

import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;

public interface ActivityInterface {

    void createActivity(product_entity product, user_entity user, OrderEntity order);

    void orderPlacedActivity(int productId, int orderId);

    void orderConformActvity(int orderId);

    Activity getActivityByOrderId(int orderId);

    List<Activity> getActivityBySellerId();

    List<Activity> getRecentActivity();
}
