package com.hanbinggan.lesson4.peridableTask;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 * 想要通过执行器框架来执行一个周期性任务，需要一个 ScheduledExecutorService对象
 * 使用 scheduledAtFixedRate() 方法发送任务，四个参数
 * 1. 需要执行的任务 2.任务将在多久之后执行 3.执行周期 4.单位
 * PS：如果一个周期性任务需要执行 5 s，如果 设置 3 s 执行一次，则在任务执行过程中将会有两个任务实例同时存在
 * scheduledAtFixedRate() 方法返回一个 ScheduledFuture 对象，带有定时任务的相关操作方法
 * ScheduledFuture getDelay() 方法返回任务到下一次执行时所要等待的剩余时间
 * scheduledWithFixedRate() 方法 第三个参数 表示 任务上一次执行结束的时间与任务下一次开始执行的时间间隔
 * 调用shutdown() 方法后，定时任务就结束了
 * 可以通过 设置 ScheduledThreadPoolExecutor类的 setContinueExistingPeriodicTasksAfterShutdownPolicy() 传参 true 后，
 * 任务将继续执行
 */
public class Main {
    public static void main(String[] args){
        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());
        Task task=new Task("Task");
        ScheduledFuture<?>result=executor.scheduleAtFixedRate(task,1,2, TimeUnit.SECONDS);
        for(int i=0;i<10;i++){
            System.out.printf("Main: Delay: %d\n",result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        executor.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Main: Finished at %s\n",new Date());
    }
}
