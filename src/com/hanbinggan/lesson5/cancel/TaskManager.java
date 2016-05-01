package com.hanbinggan.lesson5.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by hello on 2016/5/1.
 * ForkJoinTask 类提供的 cancel() 方法允许取消一个仍没有被执行的任务，如果已经开始执行，调用 cancel() 方法无法被取消
 * cancel() 方法可以接收 一个 名 为 mayInterruptIfRunning的boolean值参数 如果为true ，则 被执行也将被 取消
 */
public class TaskManager {
    private List<ForkJoinTask<Integer>>tasks;
    public TaskManager(){
        tasks=new ArrayList<>();
    }
    public void addTask(ForkJoinTask<Integer>task){
        tasks.add(task);
    }

    public void cancelTasks(ForkJoinTask<Integer>cancelTask){
        for(ForkJoinTask<Integer>task:tasks){
            if(task != cancelTask){
                task.cancel(true);
                ((SearchNumberTask)task).writeCancelMessage();
            }
        }
    }
}
