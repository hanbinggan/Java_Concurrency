package com.hanbinggan.lesson4.taskDone;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 */
public class ExecutableTask implements Callable<String>{
    private String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        try{
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: Wating %d seconds for results.\n",this.name,duration);
            TimeUnit.SECONDS.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Hello World. I'm "+name;
    }
}
