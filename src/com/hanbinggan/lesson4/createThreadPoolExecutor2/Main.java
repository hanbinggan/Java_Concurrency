package com.hanbinggan.lesson4.createThreadPoolExecutor2;

/**
 * Created by hello on 2016/4/24.
 */
public class Main {
    public static void main(String args[]){
        Server server=new Server();
        for(int i=0;i<100;i++){
            Task task=new Task("Task "+i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
