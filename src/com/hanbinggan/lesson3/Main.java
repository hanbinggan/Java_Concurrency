package com.hanbinggan.lesson3;

import com.hanbinggan.lesson3.semaphore.Job;
import com.hanbinggan.lesson3.semaphore.PrintQueue;

/**
 * Created by hello on 2016/4/23.
 */
public class Main {
    public static void main(String argc[]){
        /**
         * 3.2 semaphore
         */
        PrintQueue printQueue=new PrintQueue();
        Thread threads[]=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(new Job(printQueue));
        }
        for(int i=0;i<10;i++){
            threads[i].start();
        }
    }
}
