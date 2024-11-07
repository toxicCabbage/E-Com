package com.trashcode.crash.walletServices;

import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.Transaction.TransactionRepo;
import com.trashcode.crash.Transaction.transactionService;
import com.trashcode.crash.userServices.UserService;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;
import com.trashcode.crash.wallet.Wallet;
import com.trashcode.crash.wallet.walletRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class walletService implements walletServies {

    @Autowired
    private getCurrentUserId GCUID;

    @Autowired
    private user_repository uRepo;

    @Autowired
    private walletRepo walletrepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private transactionService transactionsercvice;

    @Autowired
    private UserService userService;

    @Override
    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        user_entity user = new user_entity();
        user = uRepo.findAllByuserid(GCUID.getCurrentUserID());

        if (alreadyHaveWallet()) {
            wallet.setUser(user);
            wallet.setAmount(0);
            walletrepo.save(wallet);
            System.out.println(
                    "***************************** wallet created successfull ****************************************");
        }

        Wallet wal = walletrepo.findByUser(user);

        return wal;
    }

    @Override
    public boolean alreadyHaveWallet() {

        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());
        Wallet result = walletrepo.findByUser(user);

        if (result == null) {
            System.out.println("wallet not found");
            return true;
        } else {
            System.out.println("already have wallet" + result);
            return false;
        }

    }

    @Override
    public void addMoney(int money) {

        Wallet wallet = new Wallet();
        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());

        int currentBlance = currentWalletBlance();

        int finalAmount = currentBlance += money;

        System.out.println("final Amount");

        walletrepo.updateBalance(finalAmount, user);

        System.out.println("wallet entity saved successfully");

        transactionsercvice.addMoneytoWallet(money);

    }

    @Override
    public Wallet getWallet() {

        Wallet wallet = new Wallet();
        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());

        wallet = walletrepo.findByUser(user);

        return wallet;
    }

    @Override
    public int currentWalletBlance() {
        int userID = GCUID.getCurrentUserID();
        int currentBalance = walletrepo.getCurrentBlance(userID);
        System.out.println("CURRENT BALANCE " + currentBalance);
        return currentBalance;
    }

    @Override
    public void subtractMoney(int money) {

        Wallet wallet = new Wallet();
        user_entity user = new user_entity();
        user.setUserid(GCUID.getCurrentUserID());

        int currentBlance = currentWalletBlance();

        int finalAmount = currentBlance - money;

        walletrepo.updateBalance(finalAmount, user);

        System.out.println("wallet entity saved successfully");

        transactionsercvice.addMoneytoWallet(money);

    }

    @Override
    public void addMoneyByuser(user_entity user, int money) {
        // TODO Auto-generated method stub
        int currentBlance = getCurrentBlanceByUserId(user.getUserid());
        currentBlance += money;
        walletrepo.updateBalance(currentBlance, user);

    }

    @Override
    public Wallet getWalletByUserId(int id) {

        Wallet wallet = walletrepo.findByUser(userService.getUsesrByUserId(id));

        return wallet;
    }

    @Override
    public int getCurrentBlanceByUserId(int user) {
        // TODO Auto-generated method stub
        return walletrepo.getCurrentBlance(user);
    }
}