package com.hanbinggan.lesson3.cyclic_barrier;

/**
 * Created by hello on 2016/4/24.
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult=0;
        System.out.printf("Grouper: Processing result...\n");
        int data[]=result.getData();
        for(int number:data){
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n",finalResult);
    }
}
