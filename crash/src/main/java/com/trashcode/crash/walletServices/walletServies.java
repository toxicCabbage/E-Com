package com.trashcode.crash.walletServices;

import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.wallet.Wallet;

public interface walletServies {

    public Wallet createWallet();

    public boolean alreadyHaveWallet();

    public void addMoney(int money);

    public Wallet getWallet();

    public Wallet getWalletByUserId(int userId);

    public int currentWalletBlance();

    public int getCurrentBlanceByUserId(int user);

    public void subtractMoney(int money);

    public void addMoneyByuser(user_entity user, int money);

}
