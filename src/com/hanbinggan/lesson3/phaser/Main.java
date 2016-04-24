package com.hanbinggan.lesson3.phaser;

import java.util.concurrent.Phaser;

/**
 * Created by hello on 2016/4/24.
 */
public class Main {
    public static void main(String args[]){
        Phaser phaser=new Phaser(3);

        FileSearch system=new FileSearch("C:\\Windows","log",phaser);
        FileSearch apps=new FileSearch("C:\\Program Files","log",phaser);
        FileSearch user=new FileSearch("C:\\Users\\hello","log",phaser);

        Thread systemThread=new Thread(system,"System");
        systemThread.start();

        Thread appsThread=new Thread(apps,"Apps");
        appsThread.start();

        Thread userThread=new Thread(user,"User");
        userThread.start();

        try {
            systemThread.join();
            appsThread.join();
            userThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Terminated: "+phaser.isTerminated());
    }
}
