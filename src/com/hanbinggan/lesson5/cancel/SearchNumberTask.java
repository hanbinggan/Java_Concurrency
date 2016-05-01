package com.hanbinggan.lesson5.cancel;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/5/1.
 */
public class SearchNumberTask extends RecursiveTask<Integer>{
    private int numbers[];
    private int start;
    private int end;
    private int number;
    private TaskManager manager;
    private final static int NOT_FOUND=-1;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Task: %d: %d\n",start,end);
        int ret=0;
        if(end-start>10){
            ret=launchTasks();
        }else{
            ret=lookForNumber();
        }
        return ret;
    }
    private int lookForNumber(){
        for(int i=start;i<end;i++){
            if(numbers[i]==number){
                System.out.printf("Task: Number %d found in position %d\n",number,i);
                manager.cancelTasks(this);
                return i;
            }
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return NOT_FOUND;
    }
    private int launchTasks(){
        int mid=(start+end)/2;
        SearchNumberTask task1=new SearchNumberTask(numbers,start,mid,number,manager);
        SearchNumberTask task2=new SearchNumberTask(numbers,mid,end,number,manager);

        manager.addTask(task1);
        manager.addTask(task2);

        task1.fork();
        task2.fork();

        int ret=task1.join();
        if(ret != -1){
            return ret;
        }
        ret = task2.join();
        return ret;
    }
    public void writeCancelMessage(){
        System.out.printf("Task: Cancelled task from %d to %d\n",start,end);
    }
}
