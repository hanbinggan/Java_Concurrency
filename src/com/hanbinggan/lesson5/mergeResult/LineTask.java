package com.hanbinggan.lesson5.mergeResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hello on 2016/5/1.
 */
public class LineTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID=1L;
    private String line[];
    private int start;
    private int end;
    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result=0;
        if(end-start<100){
            result=count(line,start,end,word);
        }else{
            int middle=(start+end)/2;
            LineTask task1=new LineTask(line,start,middle,word);
            LineTask task2=new LineTask(line,middle,end,word);
            invokeAll(task1,task2);
            try{
                result=groupResults(task1.get(),task2.get());
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Line: %s %d\n",Thread.currentThread().getName(),result);
        return result;
    }
    private Integer count(String[] line,int start,int end,String word){
        int counter=0;
        for(int i=start;i<end;i++){
            if(line[i].equals(word)){
                counter++;
            }
        }
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return counter;
    }
    private Integer groupResults(Integer number1,Integer number2){
        return number1+number2;
    }
}
