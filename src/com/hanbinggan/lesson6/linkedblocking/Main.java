package com.hanbinggan.lesson6.linkedblocking;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingDeque 阻塞式数据结构 生成的时候指定了固定容量的大小
 * 使用 put() 方法，如果队列已满则调用这个方法的线程将被阻塞知道列表中有了可用空间
 * 使用 take() 方法，如果队为空将一直阻塞到队列不为空
 * 如果被中断则会 抛出 InterruptedException异常
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String>list=new LinkedBlockingDeque<>(3);
        Client client=new Client(list);
        Thread thread=new Thread(client);
        thread.start();

        for (int i=0;i<5;i++){
            for (int j=0;j<3;j++){
                String request=list.take();
                System.out.printf("Main: Request: %s at %s. Size: %d\n",request,new Date(),list.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }
        System.out.printf("Main: End of the program.\n");
    }
}
