package com.hanbinggan.lesson1;

import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/22.
 */
public class Task13 implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
