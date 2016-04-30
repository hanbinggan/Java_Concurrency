package com.hanbinggan.lesson4.peridableTask;

import java.util.Date;

/**
 * Created by hello on 2016/4/30.
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n",name,new Date());
    }
}
