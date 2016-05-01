package com.hanbinggan.lesson5.exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/5/1.
 *
 */
public class Main {
    public static void main(String[] args){
        int array[]=new int[100];
        Task task=new Task(array,0,array.length);

        ForkJoinPool pool=new ForkJoinPool();
        pool.execute(task);

        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(task.isCompletedAbnormally()){
            System.out.printf("Main: An exception has occurred.\n");
            System.out.printf("Main: %s\n",task.getException());
        }
        System.out.printf("Main: Result: %d\n",task.join());
    }
}
