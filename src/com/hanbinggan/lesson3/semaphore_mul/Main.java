package com.hanbinggan.lesson3.semaphore_mul;

/**
 * Created by hello on 2016/4/23.
 */
public class Main {
    public static void main(String argc[]){
        com.hanbinggan.lesson3.semaphore_mul.PrintQueue printQueue=
                new com.hanbinggan.lesson3.semaphore_mul.PrintQueue();
        Thread threads[]=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(new com.hanbinggan.lesson3.semaphore_mul.Job(printQueue));
        }
        for(int i=0;i<10;i++){
            threads[i].start();
        }
    }
}
