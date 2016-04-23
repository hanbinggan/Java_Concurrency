package com.hanbinggan.lesson3.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by hello on 2016/4/23.
 * Semaphore
 * 1.必须通过 acquire()方法获得信号量
 * 2.使用共享资源执行必要的操作
 * 3.通过release()方法释放信号量
 * 线程在被阻塞期间，可能被中断，从而导致acquire()方法抛出中断异常
 * acquireUniterruptibly() 忽略线程中断并不抛出异常
 * tryAcquire()试图获得信号量，能获得是true，不能获得是false
 * 信号量构造器提供第二个传入参数，表示是否为公平模式
 */
public class PrintQueue {
    private final Semaphore semaphore;
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
