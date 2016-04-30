package com.hanbinggan.lesson4.taskDone;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by hello on 2016/4/30.
 */
public class ResultTask extends FutureTask<String> {
    private String name;
    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name=((ExecutableTask)callable).getName();
    }
    @Override
    protected void done(){
        if(isCancelled()){
            System.out.printf("%s: Has been canceled\n",name);
        }else{
            System.out.printf("%s: Has finished.\n",name);
        }
    }
}
