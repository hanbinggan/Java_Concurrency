package com.hanbinggan.lesson1;

/**
 * Created by hello on 2016/4/22.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s : %s\n",e.getClass(),e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace();
        System.out.printf("Thread status: %s\n",t.getState());
    }
}
