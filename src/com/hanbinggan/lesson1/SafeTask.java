package com.hanbinggan.lesson1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/22.
 * 线程的局部变量分别为每个线程存储了各自的属性值，并提供给每个线程使用
 * 可以通过get()方法获得这个值，也可以通过set方法设置
 * 第一次没有初始化，则会调用initialValue() 返回当前时间
 * 线程局部变量提供了remove方法，为访问这个变量线程删除已经存储的值
 * InheritableThreadLocal类 这个类提供子线程继承的值
 * 可以覆盖 childValue()方法，初始化子线程在线程局部变量中的值，子线程使用父线程的值作为传入参数
 */
public class SafeTask implements Runnable{
    private static ThreadLocal<Date>startDate=new ThreadLocal<Date>(){
        protected Date initialValue(){
            return new Date();
        }
    };
    @Override
    public void run() {
        System.out.printf("Starting Thread %s: %s\n",Thread.currentThread().getId(),startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Thread finished %s: %s\n",Thread.currentThread().getId(),startDate.get());
    }
}
