package com.hanbinggan.lesson1;

import java.util.Random;

/**
 * Created by hello on 2016/4/22.
 */
public class Task12 implements Runnable {
    @Override
    public void run() {
        int result;
        Random random=new Random(Thread.currentThread().getId());
        while (true){
            result=1000/(int)(random.nextDouble()*100);
            System.out.printf("%s: %d\n",Thread.currentThread().getId(),result);
            if(Thread.currentThread().isInterrupted()){
                System.out.printf("%d: Interrupted\n",Thread.currentThread().getId());
                return;
            }
        }
    }
}
