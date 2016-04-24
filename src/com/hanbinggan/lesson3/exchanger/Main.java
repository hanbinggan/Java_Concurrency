package com.hanbinggan.lesson3.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hello on 2016/4/24.
 */
public class Main {
    public static void main(String args[]){
        List<String>buffer1=new ArrayList<>();
        List<String>buffer2=new ArrayList<>();

        Exchanger<List<String>>exchanger=new Exchanger<>();

        Producer producer=new Producer(buffer1,exchanger);
        Consumer consumer=new Consumer(buffer2,exchanger);

        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
