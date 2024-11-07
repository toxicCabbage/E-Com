package com.trashcode.crash.Stats;

import java.time.LocalDate;
import java.util.List;

import com.trashcode.crash.Transaction.Transactions;

public interface StatisticsInterface {

    List<Transactions> getMonthlyTransactions(LocalDate currentDate, LocalDate previousDate);

    int getTotalMonthlyIncome(List<Transactions> transactions);

    int getPendingProducts();

    int getAllProuductCountByUserId();

    int getAllOrderCountByUserId();

    int getAllProductRequestCountByUserId();

    long getSelletTotalEarning();

    long getAllProductsCount();

    long getAllOrderCount();

    long getUserCountByRole();

}
