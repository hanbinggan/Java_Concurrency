package com.hanbinggan.lesson1;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/21.
 */
public class WriterTask implements Runnable {
    private Deque<Event>deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            Event event=new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has gemerated an event",Thread.currentThread().getId()));
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
