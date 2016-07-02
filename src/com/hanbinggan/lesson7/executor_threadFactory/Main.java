package com.hanbinggan.lesson7.executor_threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("myThreadFactory");
        ExecutorService executor = Executors.newCachedThreadPool(factory);
        MyTask task = new MyTask();
        executor.submit(task);
//        executor.execute(task);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.printf("Main: End of the program.\n");
    }
}
