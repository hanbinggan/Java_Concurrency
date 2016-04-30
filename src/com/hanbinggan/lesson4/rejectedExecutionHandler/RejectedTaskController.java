package com.hanbinggan.lesson4.rejectedExecutionHandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hello on 2016/4/30.
 * rejectedExecution() 方法 Runnable 存储被拒绝的任务，Executor 存储被拒绝的执行器
 */
public class RejectedTaskController implements RejectedExecutionHandler  {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: The task %s has been rejected.\n",r.toString());
        System.out.printf("RejectedTaskController: %s\n",executor.toString());
        System.out.printf("RejectedTaskController: Terminating: %s\n",executor.isTerminating());
        System.out.printf("RejectedTaskController: Terminated: %s\n",executor.isTerminated());
    }
}
