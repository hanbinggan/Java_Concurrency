package com.hanbinggan.runMd5;

/**
 * Created by hello on 2016/4/17.
 */
public class StartCal {
    public static void main(String argc[]){
        int mx=5;
        Thread threads[]=new Thread[mx];
        for(int i=0;i<mx;i++){
            threads[i]=new Thread(new Md5Cal());
            threads[i].start();
        }
        boolean finish=false;
        while(!finish){
            finish=true;
            for(int i=0;i<mx;i++){
                if(threads[i].getState()!= Thread.State.TERMINATED){
                    finish=false;
                }
            }
        }
        for(int i=100;i<Md5Cal.ans.length;i++){
            System.out.println(Md5Cal.ans[i]);
        }
    }
}
