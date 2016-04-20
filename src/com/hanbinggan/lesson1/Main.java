package com.hanbinggan.lesson1;

import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/20.
 */
public class Main {
    public static void main(String argc[]){
        /*
        //interrupted
        Thread task=new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        task.interrupt();*/

       /* //thread interrupted control
        FileSearch search=new FileSearch("E:\\IdeaProjects\\now\\mul_thread",".gitignore");
        Thread thread=new Thread(search);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();*/

        //sleep and awake
        FileClock clock =new FileClock();
        Thread thread=new Thread(clock);
        thread.start();
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
