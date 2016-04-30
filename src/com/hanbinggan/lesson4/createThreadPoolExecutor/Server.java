package com.hanbinggan.lesson4.createThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hello on 2016/4/24.
 * newCachedThreadPool() 方法创建了一个缓存线程池，这个方法返回一个 ExecutorService 对象
 * 如果需要执行新任务，，线程池就会创建新线程
 * 如果线程所运行的任务执行完成后并且这个线程可用，那么缓存线程池将会重用这些线程。
 * 仅当线程的数量是合理的或者线程只会运行很短的时间时，适合采用 Executors 工厂类的 newCachedThreadPool()方法来创建执行器
 * ThreadPoolExecutor 需要显式的结束
 * getPoolSize() 方法获得线程池大小
 * getActiveCount() 方法获取线程池中活动线程的数量
 * getCompletedTaskCount() 获得执行器完成的任务数量
 * getLargestPoolSize() 返回曾经同时位于线程池中的最大线程数
 * shutdownNow() 立即关闭执行器，返回等待执行的任务列表，调用时，正在运行的任务将继续运行，但是该方法不等待这些任务完成
 * isTerminated() 执行器完成关闭过程返回 true
 * isShutdown() 如果 调用了 shutdown() 方法将返回 true
 * awaitTermination(long timeout,TimeUnit unit) 这个方法将阻塞所调用的线程，知道 执行器完成 任务 或达到指定 timeout 值
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
        executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrived.\n");
        executor.execute(task);
        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
    }
    public void endServer(){
        executor.shutdown();
    }
}
