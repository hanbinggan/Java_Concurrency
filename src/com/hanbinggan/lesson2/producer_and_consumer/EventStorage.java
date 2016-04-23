package com.hanbinggan.lesson2.producer_and_consumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hello on 2016/4/23.
 * 线程可以在同步代码块中调用wait()方法，在同步代码块之外调用则抛出 非法异常
 */
public class EventStorage {
    private int maxSize;
    private List<Date>storage;

    public EventStorage(){
        maxSize=10;
        storage=new LinkedList<>();
    }

    public synchronized void set(){
        while(storage.size()==maxSize){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set : %d\n",storage.size());
        notifyAll();
    }

    public synchronized void get(){
        while(storage.size()==0){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Get : %d: %s\n",storage.size(),((LinkedList<?>)storage).poll());
        notifyAll();
    }
}
