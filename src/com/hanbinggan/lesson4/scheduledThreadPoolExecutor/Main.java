package com.hanbinggan.lesson4.scheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 * newScheduledThreadPool() 方法传参是线程池拥有的线程数
 * 也可以使用Runnable 接口来实现任务
 * 调用 shutdown()方法仍有待处理的任务需要执行时，仍将被执行
 * setExecutionExistingDelayedTasksAfterShutdownPolicy() 传参 false 后执行 shutdown() 方法后，
 * 待处理任务将不被执行
 */
public class Main {
    public static void main(String[] args){
        ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());
        for(int i=0;i<5;i++){
            Task task=new Task("Task "+i);
            executor.schedule(task,i*2, TimeUnit.SECONDS);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Main: Ends at: %s\n",new Date());
    }
}
