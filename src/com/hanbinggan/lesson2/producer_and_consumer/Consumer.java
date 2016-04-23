package com.hanbinggan.lesson2.producer_and_consumer;

/**
 * Created by hello on 2016/4/23.
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            storage.get();
        }
    }
}
