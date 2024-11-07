package com.trashcode.crash.sellerStatus;

import java.util.ArrayList;
import java.util.List;

import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class sellerStatusService implements SellerStatsInterface {

    @Autowired
    private user_repository UserRepository;

    @Autowired
    private sellerStatusRepository sRepository;

    @Autowired
    private OrderRepo oRepo;

    @Autowired
    private sellerStatusRepository SsRepo;

    @Override
    public void createSellerStats(int sellerId) {

        System.out.println("CREATING SELLER STATE");

        sellerStats sStats = new sellerStats();
        user_entity user = UserRepository.findByuserid(sellerId);

        sStats.setSeller(user);
        sStats.setTotalOrders(0);

        sRepository.save(sStats);

    }

    @Override
    public void addOrderCount(int OrderId) {
        System.out.println("SELLER ORDER COUNT " + "ORDER ID " + OrderId);

        int sellerId = oRepo.findSellerIdByOrderId(OrderId);

        user_entity seller = new user_entity();
        seller.setUserid(sellerId);
        System.out.println("SELLER ID " + seller);

        int currentOrderCount;

        sellerStats value = sRepository.findBySeller(seller);

        System.out.println("VALUE ::::::::::" + value);

        if (value == null) {
            System.out.println("SELLER STATE NOT FOUND ");
            createSellerStats(seller.getUserid());

            value = sRepository.findBySeller(seller);
        }

        System.out.println("CURRENT SELLER ORDER COUNT : " + value.getTotalOrders());

        currentOrderCount = value.getTotalOrders() + 1;

        System.out.println("FINAL SELLER ORDER COUNT : " + currentOrderCount);

        sRepository.addOrderCount(currentOrderCount, seller);

        System.out.println("ORDER COUNT INCREESED SUCCESSFULLY");

    }

    public List<user_entity> topSeller() {

        List<sellerStats> sellerS = SsRepo.findFirst3ByOrderByTotalOrdersDesc();
        List<user_entity> seller = new ArrayList<>();
        user_entity user = new user_entity();

        for (sellerStats s : sellerS) {
            System.out.println("SELLER NAME : " + s.getSeller().getUsername());
            user = s.getSeller();
            seller.add(user);
        }
        for (user_entity u : seller) {
            System.out.println("SELLER ::::: " + u.getUsername());
        }

        return seller;

    }

}
