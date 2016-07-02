package com.hanbinggan.lesson7.threadFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("myThreadFactory");
        MyTask task = new MyTask();
        Thread thread = factory.newThread(task);
        thread.start();
        thread.join();

        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");

    }
}
