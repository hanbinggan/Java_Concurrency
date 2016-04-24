package com.hanbinggan.lesson3.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hello on 2016/4/24.
 * CountDownLatch 有三个基本元素
 * 1.初始值，定义必须等待的先行完成的操作数目
 * 2.await()方法，需要等待其他时间先完成的线程调用
 * 3.countDown(),每个被等待的线程完成时候调用
 * 当countDown()方法被调用后，计数器减 1 ，当计数器到达0 则 CountDownLatch 对象将唤起所有在await()方法上等待的线程
 * CountDownLatch 是用来同步执行多个任务的一个或多个线程
 * CountDownLatch 只准进入一次，当计数器到达 0 ，再调用方法将不起作用，如果要做类似的同步，就必须创建一个新的 CountDownLatch对象
 * CountDownLatch 不可以被重置，只能使用一次，使用完之后 需要新建
 * await(long time,TimeUnit unit) 方法被调用 ，线程将休眠直至中断，或者计数器为 0 ，或者指定时间过期
 */
public class Videoconference implements Runnable {
    private final CountDownLatch controller;
    public Videoconference(int number){
        controller=new CountDownLatch(number);
    }
    public void arrive(String name){
        System.out.printf("%s has arrived.\n",name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
    }
    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
        try{
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
