package com.trashcode.crash.productStats;

import java.util.List;

import com.trashcode.crash.product_entity.product_entity;

public interface ProductStatsService {

    void createProductStats(int productId);

    void addOrderCount(int productId);

    void addViewCount(int productId);

    List<product_entity> getTopOrderedProducts();

}
