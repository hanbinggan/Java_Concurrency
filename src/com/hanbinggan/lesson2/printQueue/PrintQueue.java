package com.hanbinggan.lesson2.printQueue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hello on 2016/4/23.
 * Lock接口（和它的实现类ReentrantLock）还提供了另一个方法来获取锁 tryLock()方法。
 * 与lock()方法不同的是 ：线程使用 tryLock()不能够获取锁，trylock()不能够获取锁，而是立即返回
 * 它并不会将线程置入休眠。
 * tryLock()方法返回一个 布尔值，表示是否获取锁
 * ReentrantLock类允许递归调用
 */
public class PrintQueue {
    private final Lock queueLock=new ReentrantLock();

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
    }
}
