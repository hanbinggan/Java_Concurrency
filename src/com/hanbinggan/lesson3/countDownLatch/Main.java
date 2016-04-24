package com.hanbinggan.lesson3.countDownLatch;

/**
 * Created by hello on 2016/4/24.
 * 视频会议同步
 */
public class Main {
    public static void main(String args[]){
        Videoconference conference=new Videoconference(10);
        Thread conferenceThread=new Thread(conference);
        conferenceThread.start();
        for(int i=0;i<10;i++){
            Participant p=new Participant(conference,"Participant "+i);
            Thread thread=new Thread(p);
            thread.start();
        }
    }
}
