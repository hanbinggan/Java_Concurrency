package com.hanbinggan.lesson4.cancelTask;

import java.util.concurrent.Callable;

/**
 * Created by hello on 2016/4/30.
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while(true){
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }
}
