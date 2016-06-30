package com.hanbinggan.lesson6.threadLoacalRandom;
/**
 * ThreadLocalRandom 类 线程本地变量
 * 每一个生成随机数的线程都有一个不同的生成器，但是都在同一个类被管理
 * 相比较使用Random对象为所有线程生成随机数，该机制有更好的性能
 */

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable{
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name=Thread.currentThread().getName();
        for(int i=0;i<10;i++){
            System.out.printf("%s: %s\n",name,ThreadLocalRandom.current().nextInt(10));
        }
    }
}
