package com.hanbinggan.lesson2.producer_and_consumer;

/**
 * Created by hello on 2016/4/23.
 */
public class Producer implements Runnable {
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            storage.set();
        }
    }
}
