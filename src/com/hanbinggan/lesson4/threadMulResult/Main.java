package com.hanbinggan.lesson4.threadMulResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hello on 2016/4/30.
 * invokeAll() 方法等待所有任务的完成，
 * 改方法接收一个 Callable 对象列表，并返回一个Future对象列表
 * 在这个列表中，每一个任务对应一个Future对象
 * Future 列表中的对象与 Callable 列表中的任务一一对应
 * invokeAll() 方法通过 Future对象来获取结果
 * invokeAll(Collection<? extends Callable<T>>tasks,long timeout,TimeUnit unit); 在规定时间内返回
 * 否则中断
 */
public class Main {
    public static void main(String[] args){
        ExecutorService executor=(ExecutorService) Executors.newCachedThreadPool();
        List<Task> taskList=new ArrayList<>();
        for(int i=0;i<3;i++){
            Task task=new Task(String.format("%d",i));
            taskList.add(task);

        }
        List<Future<Result>> resultList=null;
        try {
            resultList=executor.invokeAll(taskList);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("Main: Printing the results\n");
        for(int i=0;i<resultList.size();i++){
            Future<Result> future=resultList.get(i);
            try {
                Result result=future.get();
                System.out.printf("%s: %s\n",result.getName(),result.getValue());
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
    }
}
