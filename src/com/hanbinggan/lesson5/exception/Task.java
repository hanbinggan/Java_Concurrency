package com.hanbinggan.lesson5.exception;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/5/1.
 * 当子任务抛出异常，将会影响它的父任务
 * 如果不抛出异常，可以使用 Exception e=new Exception("fdsdfsf")
 * completeExceptionally(e) 得到同样结果
 */
public class Task extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Task: Starting from %d to %d\n",start,end);
        if(end-start<10){
            if((3>start)&&(3<end)){
                throw new RuntimeException("This task throws an Exception: Task from "+ start+"to "+end);
            }
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else{

            int mid=(end+start)/2;
            Task task1=new Task(array,start,mid);
            Task task2=new Task(array,mid,end);
            invokeAll(task1,task2);
        }
        System.out.printf("Task: End from %d to %d\n",start,end);
        return 0;
    }
}
