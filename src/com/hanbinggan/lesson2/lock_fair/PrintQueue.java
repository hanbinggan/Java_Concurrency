package com.hanbinggan.lesson2.lock_fair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hello on 2016/4/23.
 *在公平模式下，当有很多线程在等待锁，锁将选择等待时间最长的线程访问临界区
 * 该模式只适用于lock() unlock()方法
 */
public class PrintQueue {
    //公平模式
    private final Lock queueLock=new ReentrantLock(true);

    public void printJob(Object document){
        queueLock.lock();
        try{
            Long duration=(long)(Math.random()*10000);
            System.out.printf("%s : PrintQueue: Printing a Job during %d second\n",
                    Thread.currentThread().getName(),duration/1000);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
        queueLock.lock();
        try{
            Long duration=(long)(Math.random()*10000);
            System.out.printf("%s : PrintQueue: Printing a Job during %d second\n",
                    Thread.currentThread().getName(),duration/1000);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
