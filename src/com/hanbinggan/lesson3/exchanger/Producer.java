package com.hanbinggan.lesson3.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hello on 2016/4/24.
 * Exchanger 允许在并发任务之间交换数据
 * Exchanger 类 允许 在两个线程之间定义同步点 ，当 两个线程 都到达同步点 则 它们交换数据
 * Exchanger 另外 exchange 方法 exchange(V data,long time,TimeUnit unit) 方法
 * 线程将休眠至 1. 被中断 2.交换的线程到达 3.耗费了指定时间
 */
public class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>>exchanger;

    public Producer(List<String> buffer,Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle=1;
        for(int i=0;i<10;i++){
            System.out.printf("Producer: Cycle %d\n",cycle);
            for(int j=0;j<10;j++){
                String message="Event "+(i*10+j);
                System.out.printf("Producer: %s\n",message);
                buffer.add(message);
            }
            try {
                buffer=exchanger.exchange(buffer);
                System.out.printf("Producer the length %d\n",buffer.size());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("Pruducer: %s\n",buffer.size());
            cycle++;
        }
    }
}
