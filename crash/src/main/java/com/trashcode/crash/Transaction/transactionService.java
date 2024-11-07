package com.trashcode.crash.Transaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.TreeNode;
import com.trashcode.crash.SellerService.SellerService;
import com.trashcode.crash.SellerService.SellerServiceImpal;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.enums.transactionCondition;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;
import com.trashcode.crash.wallet.Wallet;
import com.trashcode.crash.wallet.walletRepo;
import com.trashcode.crash.walletServices.walletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private walletRepo walletRepo;

    @Autowired
    private user_repository userRepo;

    @Autowired
    private walletService wallService;

    @Autowired
    private SellerServiceImpal sellerService;

    // upload Dir
    private String path = "C:\\Users\\NITESH AHIRE\\web app\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";

    // Default add Money image path
    private String AddMoneyImgPath = "/image/egg.png";
    LocalDate date = LocalDate.now();

    public void addMoneytoWallet(int money) {
        user_entity user = new user_entity();
        Wallet wallet = new Wallet();
        user.setUserid(GCUID.getCurrentUserID());
        wallet = walletRepo.findByUser(user);

        Transactions transactions = new Transactions();
        transactions.setTransactionAmount(money);
        transactions.setTransactionDetail("Amount Added");
        transactions.setWallet(wallet);
        transactions.setImageUrl(AddMoneyImgPath);
        transactions.setTransactionalcondition(transactionCondition.ADD_MONEY.name());
        transactions.setDate(date);
        transactionRepo.save(transactions);

        System.out.println("transaction completed successfully");
    }

    public void buyProduct(product_entity product) {
        // UserTransaction
        buyProductUserTransaction(product);

        // SellerTransaction
        buyProductSellerTransaction(product);

        // GODFATHERCUT
        buyProductGODFATHERCUT(product);

    }

    public void buyProductGODFATHERCUT(product_entity product) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

        Wallet wallet = wallService.getWalletByUserId(3);
        int GodFatherCut = (product.getProductPrice() * 20) / 100;

        System.out.println("GOTFATHER CUT : " + GodFatherCut);

        user_entity user = new user_entity();
        user.setUserid(3);

        wallService.addMoneyByuser(user, GodFatherCut);

        Transactions transactions = new Transactions();
        transactions.setDate(date);
        transactions.setTransactionalcondition(transactionCondition.INCOMING.name());
        transactions.setTransactionAmount(GodFatherCut);
        transactions.setTransactionDetail(product.getProductName() + "Cut");
        transactions.setWallet(wallet);
        transactions.setImageUrl(product.getProductImgUrl0());
        transactionRepo.save(transactions);

        System.out.println("AAMMIN");
    }

    public void buyProductSellerTransaction(product_entity product) {
        int sellerId = sellerService.getSellerIdByProductId((int) product.getProductid());
        Wallet wallet = wallService.getWalletByUserId(sellerId);
        int sellerProdfit = sellerService.sellerProfit(product.getProductPrice());

        user_entity user = new user_entity();
        user.setUserid(sellerId);

        wallService.addMoneyByuser(user, sellerProdfit);

        Transactions transactions = new Transactions();
        transactions.setTransactionAmount(sellerProdfit);
        transactions.setTransactionDetail("ProductSold : " + product.getProductName());
        transactions.setWallet(wallet);
        transactions.setDate(date);
        transactions.setTransactionalcondition(transactionCondition.INCOMING.name());
        transactions.setImageUrl(product.getProductImgUrl0());
        transactionRepo.save(transactions);
        System.out.println("SELLER TRANSACTION COMPLETED SUCCESSFULLY");

    }

    public void buyProductUserTransaction(product_entity product) {
        user_entity user = new user_entity();
        Wallet wallet = new Wallet();
        user.setUserid(GCUID.getCurrentUserID());
        wallet = walletRepo.findByUser(user);

        wallService.subtractMoney(product.getProductPrice());

        Transactions transactions = new Transactions();
        transactions.setDate(date);
        transactions.setTransactionalcondition(transactionCondition.OUTGOING.name());
        transactions.setTransactionAmount(product.getProductPrice());
        transactions.setTransactionDetail(product.getProductName());
        transactions.setWallet(wallet);
        transactions.setImageUrl(product.getProductImgUrl0());
        transactionRepo.save(transactions);

        System.out.println(
                "%%%%%%%%%%%%%%%%%%%%%%%%product successfully ordered by wallet serfvice %%%%%%%%%%%%%%%%%%%%");

    }

    public List<Transactions> getTransactions(Wallet wallet) {

        List<Transactions> transaction = transactionRepo.findByWallet(wallet);

        return transaction;

    }
}
