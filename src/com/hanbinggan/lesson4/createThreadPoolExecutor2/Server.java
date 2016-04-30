package com.hanbinggan.lesson4.createThreadPoolExecutor2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hello on 2016/4/24.
 * newFixedThreadPool() 方法创建了具有线程最大数量值的执行器，如果发送超过线程数的任务给执行器
 * 多余的任务将被阻塞，知道线程池中有空闲的线程来处理他们。
 * getPoolSize() 执行器中线程的实际数量
 * getActivityCount() 执行器正在执行任务的线程数量
 * getTaskCount() 显示有多少个任务发送给执行器
 *
 * Executors 工厂类也提供了 newSingleThreadExecutor() 方法：创建一个只有单线程的执行器，该执行器只能在同一时间执行一个任务
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
        executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
    }
    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrived.\n");
        executor.execute(task);
        System.out.printf("Server: Task Count: %d\n",executor.getTaskCount());
        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
    }
    public void endServer(){
        executor.shutdown();
    }
}
