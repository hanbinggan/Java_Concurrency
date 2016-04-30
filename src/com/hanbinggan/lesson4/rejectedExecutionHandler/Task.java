package com.hanbinggan.lesson4.rejectedExecutionHandler;

import java.util.concurrent.TimeUnit;

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
        System.out.printf("Task %s: Starting.\n",name);
        try {
            long duration=(long)(Math.random()*10);
            System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds.\n",duration);
            TimeUnit.SECONDS.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending.\n");
    }
    @Override
    public String toString(){
        return name;
    }
}
