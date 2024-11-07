package com.trashcode.crash.RecentProductServices;

import java.util.List;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.productRepo.recentProductRepo;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.product_entity.recentProduct;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecentProductManager {

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private recentProductRepo recentproductrepo;

    public void addItem(long productId) {
        product_entity product = new product_entity();
        user_entity user = new user_entity();

        product.setProductid(productId);
        user.setUserid(GCUID.getCurrentUserID());

        boolean condition = alreadyInRecentProduct(user, product);

        if (!condition) {
            recentProduct rencentproduct = new recentProduct();
            rencentproduct.setProduct(product);
            rencentproduct.setUser(user);

            recentproductrepo.save(rencentproduct);
        } else {
            System.out.println("product already in Recent product table");
        }

        // Alt path
        // recentProduct rencentproduct = new recentProduct();
        // rencentproduct.setProduct(product);
        // rencentproduct.setUser(user);
        // recentproductrepo.save(rencentproduct);

    }

    public List<recentProduct> getRecentItems() {

        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());

        // List <recentProduct> recentItems =
        // recentproductrepo.findTop5ByUserOrderByUserAsc(user);
        // List <recentProduct> recentItems =
        // recentproductrepo.findBottom5ByUserOrderByUserDesc(user);
        List<recentProduct> recentItems = recentproductrepo.findFirst5ByUserOrderByIdDesc(user);
        // System.out.println("recnet products " + recentItems);

        return recentItems;

    }

    public boolean alreadyInRecentProduct(user_entity user, product_entity product) {
        recentProduct rp = recentproductrepo.findAllRecentEntityByuserIdAndproductId(user, product);

        if (rp == null) {
            return false;
        } else {
            return true;
        }
    }

}
