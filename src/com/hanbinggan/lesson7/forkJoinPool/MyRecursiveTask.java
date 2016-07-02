package com.hanbinggan.lesson7.forkJoinPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer> {
    private int array[];
    private int start;
    private int end;

    public MyRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer ret = 0;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        if (end - start < 100) {
            for (int i = start; i < end; i++) {
                ret += array[i];
            }
            return ret;
        }
        int mid = (start + end) / 2;
        MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
        MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
        invokeAll(task1, task2);
        return addResults(task1, task2);
    }

    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        try {
            value = task1.get() + task2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }
}
