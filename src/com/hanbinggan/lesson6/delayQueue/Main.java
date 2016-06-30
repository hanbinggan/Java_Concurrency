package com.hanbinggan.lesson6.delayQueue;
/**
 * 继承Delay接口才可以存放到DelayQueue队列中
 * 可以存放带有激活日期的元素
 * take 为空将被阻塞
 */

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Event>queue=new DelayQueue<>();
        Thread[] threads=new Thread[5];
        for(int i=0;i<threads.length;i++){
            Task task=new Task(i+1,queue);
            threads[i]=new Thread(task);
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
        for (int i=0;i<threads.length;i++){
            threads[i].join();
        }
        do{
            int counter=0;
            Event event;
            do {
                event=queue.poll();
                if(event!=null){
                    counter++;
                }
            }while (event!=null);
            System.out.printf("At %s you have read %d events\n",new Date(),counter);
            TimeUnit.MILLISECONDS.sleep(500);
        }while (queue.size()>0);
    }
}
