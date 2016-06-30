package com.hanbinggan.lesson6.threadLoacalRandom;

public class Main {
    public static void main(String[] args){
        Thread[] threads=new Thread[3];
        for(int i=0;i<threads.length;i++){
            TaskLocalRandom random=new TaskLocalRandom();
            threads[i]=new Thread(random);
            threads[i].start();
        }
    }
}
