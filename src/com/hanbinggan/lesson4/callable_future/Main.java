package com.hanbinggan.lesson4.callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hello on 2016/4/25.
 */
public class Main {
    public static void main(String args[]){
        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList=new ArrayList<>();
        Random random=new Random();

        for(int i=0;i<10;i++){
            Integer number=random.nextInt(10);
            FactorialCalculator calculator=new FactorialCalculator(number);
            /**
             * 发送一个Callable 对象给执行器去执行，submit() 方法控扼收集癖 Callable 对象 作为参数，并返回Future 对象
             * Future 对象：
             * 1.控制任务的状态，可以取消任务和检查任务是否完成，用 isDone() 方法
             * 2.使用get()方法获得 Callable 对象 call() 方法返回的结果，该方法将阻塞到 Callable call() 方法执行完成并返回结果
             * get(long time,TimeUnit unit) 如果超过等待时间则返回 null
             * get() 方法中断 抛出 InterruptedException 异常
             * 如果call() 方法异常 则 抛出 ExecutionException 异常
             * */
            Future<Integer> result=executor.submit(calculator);
            resultList.add(result);
        }
        do{
            System.out.printf("Main: Number of Completed Task: %d\n",executor.getCompletedTaskCount());
            for(int i=0;i<resultList.size();i++){
                Future<Integer> result=resultList.get(i);
                System.out.printf("Main: Task %d: %s\n",i,result.isDone());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }while(executor.getCompletedTaskCount()<resultList.size());
        System.out.printf("Main: Results\n");
        for(int i=0;i<resultList.size();i++){
            Future<Integer> result=resultList.get(i);
            Integer number=null;
            try {
                number=result.get();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }
            System.out.printf("Main: Task %d: %d\n",i,number);
        }
        executor.shutdown();
    }
}
