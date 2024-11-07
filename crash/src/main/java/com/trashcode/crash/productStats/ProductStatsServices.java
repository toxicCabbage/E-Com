package com.trashcode.crash.productStats;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import com.trashcode.crash.ProudctServices.productServices;
import com.trashcode.crash.SellerService.SellerService;
import com.trashcode.crash.SellerService.SellerServiceImpal;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.product_entity.product_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatsServices implements ProductStatsService {

    @Autowired
    private ProductStatsRepo pStatsRepo;

    // @Autowired
    // private SellerService sellerService;

    @Autowired
    private SellerServiceImpal sellerService;

    @Autowired
    private productServices pServices;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Override
    public void createProductStats(int productId) {

        ProductStats productStats = new ProductStats();
        // int sellerId = orderRepo.finduseridByOrderid(productId);
        product_entity product = pServices.getProudctByProductId(productId);

        productStats.setProduct(product);
        productStats.setSellerId(product.getUserid());
        productStats.setOrderCount(0);
        productStats.setViewCount(0);

        pStatsRepo.save(productStats);

        System.out.println("New ProductStat Entity Created Sccessfully " + productId);

    }

    @Override
    public void addOrderCount(int OrderId) {
        System.out.println("Order Counter");

        int productId = orderRepo.findProductidByOrderid(OrderId);

        int currentOrderCount;

        ProductStats value = pStatsRepo.findByProduct(pServices.getProudctByProductId(productId));

        if (value == null) {
            System.out.println("product Stats not found");
            createProductStats(productId);
            value = pStatsRepo.findByProduct(pServices.getProudctByProductId(productId));
        }
        System.out.println("CurrentOrderCount :" + value.getOrderCount());
        currentOrderCount = value.getOrderCount() + 1;
        System.out.println("final order count : " + currentOrderCount);

        pStatsRepo.addorderCount(pServices.getProudctByProductId(productId), currentOrderCount);

        System.out.println("Order Counter successfully Increesed");

    }

    @Override
    public void addViewCount(int productId) {
        System.out.println("View Counter");

        int currentOrderCount;

        ProductStats value = pStatsRepo.findByProduct(pServices.getProudctByProductId(productId));
        System.out.println(value);

        if (value == null) {
            System.out.println("product Stats not found");
            createProductStats(productId);
            value = pStatsRepo.findByProduct(pServices.getProudctByProductId(productId));
        }
        System.out.println("CurrentviewCount :" + value.getViewCount());
        currentOrderCount = value.getViewCount() + 1;
        // currentOrderCount = currentOrderCount + 1;
        System.out.println("final view count : " + currentOrderCount);

        pStatsRepo.addviewCount(pServices.getProudctByProductId(productId), currentOrderCount);

        System.out.println("view Counter successfully Increesed");
    }

    @Override
    public List<product_entity> getTopOrderedProducts() {
        System.out.println("getTop ORRFRT ---------------------------------");
        List<ProductStats> products = pStatsRepo
                .findFirst3BySellerIdOrderByOrderCountDesc(GCUID.getCurrentUserID());
        // System.out.println("tpo PRoducts : " + products);
        List<product_entity> product = new ArrayList<>();

        for (ProductStats p : products) {
            product.add(p.getProduct());
            System.out.println("------------------------------------------");
            System.out.println(p.getProduct().getProductName());
        }
        return product;
    }

    public List<product_entity> getTopProducts() {
        System.out.println("getTop ORRFRT ---------------------------------");
        List<ProductStats> products = pStatsRepo
                .findFirst3ByOrderByOrderCountDesc();
        // System.out.println("tpo PRoducts : " + products);
        List<product_entity> product = new ArrayList<>();

        for (ProductStats p : products) {
            product.add(p.getProduct());
            System.out.println("------------------------------------------");
            System.out.println(p.getProduct().getProductName());
        }
        return product;
    }

}
