package com.hanbinggan.lesson4.treadFirstResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hello on 2016/4/25.
 * invokeAny()方法接收到一个任务列表然后运行任务
 * 并返回第一个完成任务并且没有抛出异常的任务执行结果
 * 该方法的返回值与任务里call()方法返回的类型相同
 * 如果有返回正确的结果则invokeAny()方法返回首先出现的正确结果
 * 如果没有 则 抛出 ExecutionException 异常
 * invokeAny(Collection<? extends Callable<T>>task,long timeout,TimeUnit unit) 在指定时间返回结果
 * 否则抛出异常
 */
public class Main {
    public static void main(String args[]){
        String user="test";
        String password="test";

        UserValidator ldapValidator=new UserValidator("LDAP");
        UserValidator dbValidator=new UserValidator("DataBase");

        TaskValidator ldapTask=new TaskValidator(ldapValidator,user,password);
        TaskValidator dbTask=new TaskValidator(dbValidator,user,password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executor=(ExecutorService) Executors.newCachedThreadPool();

        String result;
        try {
            result=executor.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n",result);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("Main: End of the Execution.\n");
    }

}
