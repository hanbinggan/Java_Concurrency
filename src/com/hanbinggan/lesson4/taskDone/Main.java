package com.hanbinggan.lesson4.taskDone;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 * 当 任务执行结束，FutureTask 类会调用 done()方法
 * 在创建和 返回值 以及改变任务状态 为 isDone 之哈偶 FutureTask 类 就会在内部调用 done()方法
 * 可以用来产生报表，通过邮件发送结果，释放一些系统资源等操作
 */
public class Main {
    public static void main(String[] args){
        ExecutorService executor=(ExecutorService) Executors.newCachedThreadPool();
        ResultTask[] resultTasks=new ResultTask[5];
        for(int i=0;i<5;i++){
            ExecutableTask executableTask=new ExecutableTask("Task "+i);
            resultTasks[i]=new ResultTask(executableTask);
            executor.submit(resultTasks[i]);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for(int i=0;i<resultTasks.length;i++){
            resultTasks[i].cancel(true);
        }
        for(int i=0;i<resultTasks.length;i++){
            try {
                if(!resultTasks[i].isCancelled()){
                    System.out.printf("%s\n",resultTasks[i].get());
                }
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
