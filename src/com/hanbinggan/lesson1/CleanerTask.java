package com.hanbinggan.lesson1;

import java.util.Date;
import java.util.Deque;

/**
 * Created by hello on 2016/4/21.
 * save event
 * constructor
 * protected thread
 */
public class CleanerTask extends Thread {
    private Deque<Event>deque;
    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        //守护线程
        System.out.printf("protected thread started");
        setDaemon(true);
    }

    @Override
    public void run(){
        while (true){
            Date date=new Date();
            clean(date);
        }
    }
    private void clean(Date date){
        long difference;
        boolean delete;
        if(deque.size()==0){
            return;
        }
        delete=false;
        do{
            Event e=deque.getLast();
            difference=date.getTime()-e.getDate().getTime();
            if(difference>10000){
                System.out.println("Cleaner: "+e.getEvent());
                deque.removeLast();
                delete=true;
            }
        }while(difference>10000);
        if(delete){
            System.out.println("Cleaner size of the queue: "+deque.size());
        }
    }

}
