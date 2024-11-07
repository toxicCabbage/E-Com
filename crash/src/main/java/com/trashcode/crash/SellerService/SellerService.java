package com.trashcode.crash.SellerService;

import java.util.List;

import com.trashcode.crash.product_entity.product_entity;

public interface SellerService {

    public List<product_entity> SellerProduct(int userId);

    public void DeleteProductByProudctId(int proudctId);

    public int getSellerIdByProductId(int productId);

    public int sellerProfit(int productPrice);

}
