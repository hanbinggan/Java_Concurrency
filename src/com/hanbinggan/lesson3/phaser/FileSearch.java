package com.hanbinggan.lesson3.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/24.
 * Phaser 类 不必对它的 方法进行异常处理，被 Phaser 类 置于休眠的线程 不会响应中断事件，不会抛出 中断异常
 * Phaser 中 arrive()方法通知 phaser 对象一个参与者完成了当前阶段，但是 该线程 不会等待其他参与者都完成当前阶段
 * awaitAdvance(int phase): 如果传入的阶段和当前阶段一致，则将当前线程休眠知道其他线程完成，如果不一致则返回
 * awaitAdvanceInterruptibly(int phase):跟 awaitAdvance(int phase)方法一直，但是休眠过程中被中断将会抛出异常
 * Phaser 类提供 register() 方法将 新的参与者 注册到 Phaser 中，参与者被当作没有完成本阶段的线程
 * bulkRegister() 方法 将 指定 数目的 参与者 注册到 phaser 中 ，参与者被当作没有完成本阶段的线程
 * Phaser 类 提供 forceTermination() 方法 强制终止状态，phaser 进入终止态，
 * 则 awaitAdvance() arriveAndAwaitAdvance() 方法返回负值，可以判断
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results=new ArrayList<>();
    }
    private void directoryProcess(File file){
        File list[]=file.listFiles();
        if(list != null){
            for(int i=0;i<list.length;i++){
                if(list[i].isDirectory()){
                    directoryProcess(list[i]);
                }else{
                    fileProcess(list[i]);
                }
            }
        }
    }
    private void fileProcess(File file){
        if(file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }
    private void filterResults(){
        List<String> newResults=new ArrayList<>();
        long actualDate=new Date().getTime();
        for(int i=0;i<results.size();i++){
            File file=new File(results.get(i));
            long fileDate=file.lastModified();
            if(actualDate-fileDate< TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)){
                newResults.add(results.get(i));
            }
        }
        results=newResults;
    }

    private boolean checkResults(){
        if(results.isEmpty()){
            System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n",Thread.currentThread().getName(),phaser.getPhase());
            //通知当前phaser线程已经结束这个阶段，将不参于接下来的阶段操作
            phaser.arriveAndDeregister();
            return false;
        }else{
            System.out.printf("%s: Phase %d: %d results.\n",Thread.currentThread().getName()
                    ,phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
    private void showInfo(){
        for(int i=0;i<results.size();i++){
            File file=new File(results.get(i));
            System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsoluteFile());
        }
        phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        //所以线程都创建之后再开始
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
        File file=new File(initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResults()){
            return;
        }
        filterResults();
        if(!checkResults()){
            return;
        }
        showInfo();
        //结束
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());
    }
}
