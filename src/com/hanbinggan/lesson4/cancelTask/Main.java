package com.hanbinggan.lesson4.cancelTask;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 * 可以使用Future 接口的 cancel() 方法，取消一个已经发送给执行器的任务
 * 其中： 如果任务已经完成，或之前已经被取消，或由于某种原因不能被取消 ，则方法将返回false 并且任务不能取消
 * 如果 任务 在执行器 中等待分配 Thread 对象来执行它，那么任务将被取消
 * 如果任务已经在运行，则依赖于 cancel()方法传递的参数，如果 为 true 且 任务正在 执行，则任务取消
 * 如果为 false 且 正在执行 这任务将不会被取消
 * 如果 任务被取消 ，使用 Future 类 的 get() 方法将 抛出 CancellationException 异常
 */
public class Main {
    public static void main(String[] args){
        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task=new Task();
        System.out.printf("Main: Executing the Task\n");
        Future<String> result=executor.submit(task);
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Main: Canceling the Task\n");
        result.cancel(true);
        System.out.printf("Main: Cancelled: %s\n",result.isCancelled());
        //取消了所以算是任务已完成
        System.out.printf("Main: Done: %s\n",result.isDone());
        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");
    }
}
