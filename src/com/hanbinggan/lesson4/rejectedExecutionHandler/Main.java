package com.hanbinggan.lesson4.rejectedExecutionHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hello on 2016/4/30.
 * setRejectedExecutionHandler() 方法设置用于被拒绝的任务的处理程序
 * 如果拒绝任务，有 setRejectedExecutionHandler() 方法则执行rejectedExecution() 方法
 * 否则 抛出 RejectedExecutionException 异常
 */
public class Main {
    public static void main(String[] args){
        RejectedTaskController controller=new RejectedTaskController();

        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();

        executor.setRejectedExecutionHandler(controller);

        System.out.printf("Main: Starting.\n");

        for(int i=0;i<3;i++){
            Task task=new Task("Task "+i);
            executor.submit(task);
        }

        System.out.printf("Main: Shutting down the Executor.\n");
        executor.shutdown();

        System.out.printf("Main: Sending another Task.\n");
        Task task=new Task("RejectedTask");
        executor.submit(task);

        System.out.printf("Main: End.\n");
    }
}
