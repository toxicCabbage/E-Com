package com.trashcode.crash.cartServices;

import java.util.List;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.cartRepo.cartRepo;
import com.trashcode.crash.controller.productController;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.cartEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMap;

@Service
public class cartManager {

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private cartRepo cRepo;

    @Autowired
    private productRepo pRepo;

    @Autowired
    private user_repository uRpo;

    @Autowired
    private productController pc;

    public List<cartEntity> addItem(int productId) {
        product_entity product = pRepo.findByproductid(productId);
        user_entity user = uRpo.findByuserid(GCUID.getCurrentUserID());

        boolean inCart = alreadyInCart(productId);

        int SellerId = pRepo.finduseridByproductid(productId);
        System.out.println("get the seller id");
        String SellerName = uRpo.finduserNameByuserid(SellerId);
        System.out.println("get get Seller Name");

        if (inCart) {
            cartEntity newItem = new cartEntity();
            newItem.setProduct(product);
            newItem.setUser(user);
            newItem.setQuantity(2);
            newItem.setSellerName(SellerName);
            cRepo.save(newItem);
        }

        user_entity ue = new user_entity();
        ue.setUserid(GCUID.getCurrentUserID());
        List<cartEntity> cartItems = cRepo.findByUser(ue);

        System.out.println("___________________________________-----------" + cartItems);
        System.out.println("BOFORE TRANSECTION ++++++++++++++++++++++++++==============");
        System.out.println("saved");

        return cartItems;

    }

    public List<cartEntity> goToCart() {

        user_entity ue = new user_entity();
        ue.setUserid(GCUID.getCurrentUserID());

        List<cartEntity> cartItems = cRepo.findByUser(ue);

        System.out.println("___________________________________-----------" + cartItems);
        System.out.println("BOFORE TRANSECTION ++++++++++++++++++++++++++==============");
        System.out.println("saved");

        return cartItems;

    }

    // TOtal Price

    public int totalPrice(List<cartEntity> ce) {

        int totalPrice = 0;
        System.out.println("total price " + totalPrice);

        for (cartEntity i : ce) {
            System.out.println(i.getProduct().getProductPrice());
            totalPrice += i.getProduct().getProductPrice();
        }
        System.out.println("total price " + totalPrice);

        return totalPrice;

    }

    public boolean alreadyInCart(long productId) {

        product_entity product = new product_entity();
        user_entity ue = new user_entity();

        product.setProductid(productId);
        ue.setUserid(GCUID.getCurrentUserID());

        cartEntity ce = cRepo.findAllcartEntityByuserIdAndproductId(ue, product);
        System.out.println("ce value : " + ce);
        if (ce == null) {

            System.out.println("ready to add item to cart :");
            return true;

        } else {

            System.out.println(ce);
            System.out.println("already in cart ");
            return false;
        }
    }

    public List<cartEntity> BuyEverything() {

        user_entity ue = new user_entity();
        ue.setUserid(GCUID.getCurrentUserID());

        List<cartEntity> cartItems = cRepo.findByUser(ue);

        return cartItems;
    }

    public void DeleteCartItemByUserAndProduct(int userId, int productId) {

        int itemDeleted = cRepo.deleteCartEntityByUserAndProduct(userId, productId);
        System.out.println("cart entity deleted : " + itemDeleted);

    }

}
