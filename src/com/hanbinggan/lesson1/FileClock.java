package com.hanbinggan.lesson1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/20.
 */
public class FileClock implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(new Date());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                System.out.println("The FileClock has been interrupted");
            }
        }
    }
}
