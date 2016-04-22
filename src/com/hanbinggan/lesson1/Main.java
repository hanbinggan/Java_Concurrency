package com.hanbinggan.lesson1;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/20.
 */
public class Main {
    public static void main(String argc[]){
        /*
        //interrupted
        Thread task=new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        task.interrupt();*/

       /* //thread interrupted control
        FileSearch search=new FileSearch("E:\\IdeaProjects\\now\\mul_thread",".gitignore");
        Thread thread=new Thread(search);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();*/

        /*//sleep and awake
        FileClock clock =new FileClock();
        Thread thread=new Thread(clock);
        thread.start();
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();*/

        /*//test join
        DataSourcesLoader dataSourcesLoader=new DataSourcesLoader();
        Thread thread1=new Thread(dataSourcesLoader);

        NetworkConnectionsLoader networkConnectionsLoader=new NetworkConnectionsLoader();
        Thread thread2=new Thread(networkConnectionsLoader);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Main:Configuration has been loaded:"+new Date());*/

        /*//protected thread
        Deque<Event>deque=new ArrayDeque<>();
        WriterTask writerTask=new WriterTask(deque);
        for(int i=0;i<3;i++){
            Thread thread=new Thread(writerTask);
            thread.start();
        }
        CleanerTask cleanerTask=new CleanerTask(deque);
        cleanerTask.start();*/

        //异常处理
        /*
        * 线程抛出异常并没有被捕获是。JVM检查是否被预置了未捕获的异常处理器，如果找到了，则传参
        * 首先找线程对象的未捕获处理器  setUncaughtExceptionHandler 设置的
        * 找不到找所在的线程组的未捕获异常处理器，setDefaultUncaughtExceptionHandler
        * 若无处理器存在则打印错误信息，退出
        * */
        /*Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();*/

        //线程局部变量的使用，共享
        /*UnsafeTask task=new UnsafeTask();
        for(int i=0;i<10;i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }*/

        /*SafeTask task=new SafeTask();
        for(int i=0;i<10;i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }*/

        /*//线程的分组
        ThreadGroup threadGroup=new ThreadGroup("Seatcher");
        Result result=new Result();
        SearchTask searchTask=new SearchTask(result);
        for(int i=0;i<5;i++){
            Thread thread=new Thread(threadGroup,searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Thread: %d\n",threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        Thread[] threads=new Thread[threadGroup.activeCount()];
        //获得线程组线程列表
        threadGroup.enumerate(threads);
        for (int i=0;i<threadGroup.activeCount();i++){
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }
        waitFinish(threadGroup);
        threadGroup.interrupt();*/

        /*//线程组不可控异常处理
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        Task12 task=new Task12();
        for(int i=0;i<2;i++){
            Thread t=new Thread(threadGroup,task);
            t.start();
        }*/

        //使用工厂类创建线程
        MyThreadFactory factory=new MyThreadFactory("MyThreadFactory");
        Task13 task=new Task13();

        Thread thread;
        for(int i=0;i<10;i++){
            thread=factory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats\n");
        System.out.printf("%s\n",factory.getStats());
    }

    /*//线程分组
    private static void waitFinish(ThreadGroup threadGroup){
        while (threadGroup.activeCount()>9){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }*/
}
