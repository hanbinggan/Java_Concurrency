package com.hanbinggan.lesson5.mergeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hello on 2016/5/1.
 * ForkJoinTask 类提供 complete() 方法来结束任务的执行并返回结果。该方法接受 RecursiveTask，
 * 然后在任务中调用 join后返回该对象作为结果
 * get(long timeout,TimeUnit unit) 在指定时间之内返回结果
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private String document[][];
    private int start;
    private int end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result=0;
        if(end-start<10){
            result=processLines(document,start,end,word);
        }else{
            int middle=(start+end)/2;
            DocumentTask task1=new DocumentTask(document,start,middle,word);
            DocumentTask task2=new DocumentTask(document,middle,end,word);
            invokeAll(task1,task2);
            try{
                result=groupResults(task1.get(),task2.get());
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Article: %s %d\n",Thread.currentThread().getName(),result);
        return result;
    }

    private Integer processLines(String[][]document,int start,int end,String word){
        List<LineTask> tasks=new ArrayList<>();
        for(int i=start;i<end;i++){
            LineTask task=new LineTask(document[i],0,document[i].length,word);
            tasks.add(task);
        }
        invokeAll(tasks);
        int result=0;
        for(int i=0;i<tasks.size();i++){
            LineTask task=tasks.get(i);
            try{
                result=result+task.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        return result;
    }
    private Integer groupResults(Integer number1,Integer number2){
        return number1+number2;
    }
}
