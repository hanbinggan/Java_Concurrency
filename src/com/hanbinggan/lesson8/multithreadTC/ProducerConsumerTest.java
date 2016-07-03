package com.hanbinggan.lesson8.multithreadTC;

import edu.umd.cs.mtc.MultithreadedTestCase;

import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerTest extends MultithreadedTestCase {
    private LinkedTransferQueue<String> queue;

    @Override
    public void initialize() {
        super.initialize();
        queue = new LinkedTransferQueue<>();
        System.out.printf("Test: The test has been initialized.\n");
    }

    public void thread1() {
        try {
            String ret = queue.take();
            System.out.printf("Thread 1: %s.\n", ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void thread2() {
        waitForTick(1);
        try {
            String ret = queue.take();
            System.out.printf("Thread 2: %s.\n", ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void thread3() {
        waitForTick(1);
        waitForTick(2);
        queue.put("Event 1");
        queue.put("Event 2");
        System.out.printf("Thread 3: Inserted two elements.\n");
    }

    @Override
    public void finish() {
        super.finish();
        System.out.printf("Test: End.\n");

        assertEquals(true, queue.size() == 0);
        System.out.printf("Test: Result: The queue is empty.\n");
    }
}
