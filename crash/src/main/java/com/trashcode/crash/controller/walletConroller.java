package com.trashcode.crash.controller;

import java.util.List;

import com.trashcode.crash.Transaction.Transactions;
import com.trashcode.crash.Transaction.transactionService;
import com.trashcode.crash.wallet.Wallet;
import com.trashcode.crash.walletServices.walletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u")
public class walletConroller {

    @Autowired
    private walletService walletservice;

    @Autowired
    private transactionService tService;

    @GetMapping("/createWallet")
    public ModelAndView createWallet() {

        Wallet wallet = walletservice.createWallet();

        List<Transactions> transactions = tService.getTransactions(wallet);

        ModelAndView mv = new ModelAndView("wallet");
        mv.addObject("wallet", wallet);
        mv.addObject("transactions", transactions);
        return mv;

    }

    @PostMapping("/addMoney")
    public String addMoney(@RequestParam("money") int money) {
        System.out.println("esllllllllllllljglskgjdirojjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        System.out.println("money " + money);
        walletservice.addMoney(money);
        return "redirect:/createWallet";

    }

}
