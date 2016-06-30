package com.hanbinggan.lesson6.priorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue<T> 实现阻塞式优先队列
 * 其中 T 需要实现 Comparable 接口
 */
public class Main {
    public static void main(String[] args){
        PriorityBlockingQueue<Event>queue=new PriorityBlockingQueue<>();
        Thread[] threads=new Thread[5];
        for(int i=0;i<threads.length;i++){
            Task task=new Task(i,queue);
            threads[i]=new Thread(task);
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
        for(int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Queue Size: %d\n",queue.size());

        for(int i=0;i<threads.length*1000;i++){
            Event event=queue.poll();
            System.out.printf("Thread %s: Priority %d\n",event.getThread(),event.getPriority());
        }
        System.out.printf("Main: Queue Size: %d\n",queue.size());
        System.out.printf("Main: End of the program.\n");
    }
}
