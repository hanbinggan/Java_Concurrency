package com.hanbinggan.lesson5.mergeResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/5/1.
 */
public class Main {
    public static void main(String[] args){
        DocumentMock mock=new DocumentMock();
        String searcher="the";
        String[][] document=mock.generateDocument(100,1000,searcher);
        DocumentTask task=new DocumentTask(document,0,document.length,searcher);
        ForkJoinPool pool=new ForkJoinPool();
        pool.execute(task);

        do{
            System.out.printf("*************************************\n");
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
            System.out.printf("**************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (!task.isDone());
        pool.shutdown();
        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d int the document\n",task.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}
