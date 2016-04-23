package com.hanbinggan.lesson3.semaphore_mul;

import java.util.concurrent.Semaphore;

/**
 * Created by hello on 2016/4/23.
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean freePrinters[];
    public PrintQueue(){
        semaphore=new Semaphore(1);
    }
    public void printJob(Object document){
        try {
            semaphore.acquire();
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                    Thread.currentThread().getName(),duration);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
