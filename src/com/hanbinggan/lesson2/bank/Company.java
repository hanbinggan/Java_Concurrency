package com.hanbinggan.lesson2.bank;

/**
 * Created by hello on 2016/4/23.
 */
public class Company implements Runnable{
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            account.addAmount(1000);
        }
    }

}
