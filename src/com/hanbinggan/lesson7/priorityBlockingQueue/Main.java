package com.hanbinggan.lesson7.priorityBlockingQueue;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<>();
        Producer producer = new Producer(buffer);
        Thread[] producerThreads = new Thread[10];
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i] = new Thread(producer);
            producerThreads[i].start();
        }
        Consumer consumer = new Consumer(buffer);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());

        Event myEvent = new Event("Core Event", 0);
        buffer.transfer(myEvent);
        System.out.printf("Main: My Event has been transfered.\n");
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i].join();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
//        Event myEvent1 =new Event("Core Event 2",0);
//        buffer.transfer(myEvent1);
        myEvent = new Event("Core Event 2", 0);
        buffer.transfer(myEvent);
        consumerThread.join();
        System.out.printf("Main: End of the program.\n");
    }
}
