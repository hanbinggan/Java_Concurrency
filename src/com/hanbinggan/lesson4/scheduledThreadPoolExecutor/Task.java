package com.hanbinggan.lesson4.scheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by hello on 2016/4/30.
 */
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at :%s\n",name,new Date());
        return "Hello World";
    }
}
