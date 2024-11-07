package com.trashcode.crash.Stats;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.Transaction.TransactionRepo;
import com.trashcode.crash.Transaction.Transactions;
import com.trashcode.crash.auth.Roles;
import com.trashcode.crash.enums.conformation;
import com.trashcode.crash.enums.transactionCondition;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements StatisticsInterface {

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private productRepo pRepo;

    @Autowired
    private OrderRepo ORepo;

    @Autowired
    private user_repository uRepo;

    @Override
    public List<Transactions> getMonthlyTransactions(LocalDate currentDate, LocalDate previousDate) {
        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());
        String term = transactionCondition.INCOMING.name();

        List<Transactions> transactions = transactionRepo.getTransactionBetweendatawithCondition(previousDate,
                currentDate,
                term, user);
        System.out.println("transactions : " + transactions);
        return transactions;
    }

    @Override
    public int getTotalMonthlyIncome(List<Transactions> transactions) {
        int MonthlyIncome = 0;
        for (Transactions t : transactions) {
            MonthlyIncome += t.getTransactionAmount();

        }
        return MonthlyIncome;
    }

    public void addValue(String Month, int value) {

        Map<String, Integer> data = new LinkedHashMap<>();
        System.out.println(data);
    }

    public Map<String, Integer> test() {
        Map<String, Integer> data = new LinkedHashMap<>();

        LocalDate date = LocalDate.now();
        LocalDate currentDate = date;
        LocalDate previousDate;
        LocalDate tempDate;
        for (int i = 0; i <= 6; i++) {

            previousDate = currentDate.minusMonths(1);
            System.out.println("previous Month : " + previousDate);
            String month = getMonth(previousDate);

            List<Transactions> transactions = getMonthlyTransactions(currentDate, previousDate);
            int value = getTotalMonthlyIncome(transactions);
            System.out.println("MONTH : " + month + "  " + "Income : " + value);
            // addValue(month, value);
            currentDate = previousDate;
            System.out.println("----------------------------------------");
            // System.out.println("current Month " + currentDate);
            data.put(month, value);

        }

        return data;
    }

    public String getMonth(LocalDate date) {

        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
        return month;

    }

    @Override
    public int getAllProuductCountByUserId() {
        int totalProducts = pRepo.countByUserid(GCUID.getCurrentUserID());
        System.out.println("Total Product : " + totalProducts);
        return totalProducts;
    }

    @Override
    public int getAllOrderCountByUserId() {

        int totalOrders = ORepo.countOrderByUserId(GCUID.getCurrentUserID(), conformation.DISPATCH.name());
        System.out.println("Total Orders : " + totalOrders);
        return totalOrders;
    }

    @Override
    public int getAllProductRequestCountByUserId() {
        int totalProductRequest = ORepo.countRequestedProductByUserId(GCUID.getCurrentUserID(),
                conformation.YET_TO_DISPATCH.name());
        return totalProductRequest;
    }

    @Override
    public long getSelletTotalEarning() {
        int userid = GCUID.getCurrentUserID();
        List<Transactions> transactions = transactionRepo.getSellerIncomeByUserId(userid,
                transactionCondition.INCOMING.name());
        long totalIncome = 0;

        for (Transactions t : transactions) {
            System.out.println("SOmething :  " + t.getWallet().getUser().getUsername());
            totalIncome += t.getTransactionAmount();
        }
        return totalIncome;
    }

    public List<product_entity> lowOnStock() {
        List<product_entity> proudcts = pRepo.findFirst3ByUseridOrderByStockAsc(GCUID.getCurrentUserID());
        System.out.println(proudcts);
        return proudcts;
    }

    public List<user_entity> getRecentBuyers() {
        List<user_entity> Buyers = new ArrayList<>();
        user_entity user = new user_entity();
        List<OrderEntity> orders = ORepo.findLast20BySellerIdAndConformationOrderByOrderIdDesc(
                GCUID.getCurrentUserID(),
                conformation.DISPATCH.name());
        for (OrderEntity o : orders) {
            int DoubleCount = 0;
            // checking for doubles
            for (user_entity u : Buyers) {
                if (u.getUserid() == o.getUser().getUserid()) {
                    System.out.println(o.getUser().getUserid() + "      " + u.getUserid());
                    DoubleCount++;
                } else {

                }

            }

            if (DoubleCount == 0) {
                user = uRepo.findByuserid(o.getUser().getUserid());
                Buyers.add(user);
            }

        }

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for (user_entity e : Buyers) {
            System.out.println(e.getUsername() + e.getUserid());
        }

        return Buyers;
    }

    @Override
    public long getAllProductsCount() {
        long totalProduduct = pRepo.count();
        return totalProduduct;
    }

    @Override
    public long getAllOrderCount() {
        long totalOrders = ORepo.countByConformation(conformation.DISPATCH.name());
        return totalOrders;
    }

    @Override
    public long getUserCountByRole() {
        Roles role = new Roles();
        role.setId(1);
        role.setRole("ADMIN");
        long userCount = uRepo.countByRoles(role);
        return userCount;
    }

    @Override
    public int getPendingProducts() {
        int totalProductRequest = ORepo.countRequestedProduct(
                conformation.YET_TO_DISPATCH.name());
        return totalProductRequest;
    }

}
