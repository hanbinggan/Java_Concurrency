package com.hanbinggan.lesson3.semaphore_mul;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hello on 2016/4/23.
 * 最开始调用acquire()方法的3个线程将获得对临界区的访问
 * 线程可以获得分配给打印机的编号
 * acquire(),acquireUniterruptibly(),tryAcquire(),release()方法可以传参，表示线程试图获取或释放的资源数目
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean freePrinters[];
    private Lock lockPrinters;
    public PrintQueue(){
        semaphore=new Semaphore(3);
        freePrinters=new boolean[3];
        for(int i=0;i<3;i++){
            freePrinters[i]=true;
        }
        lockPrinters=new ReentrantLock();
    }
    public void printJob(Object document){
        try {
            semaphore.acquire();
            int assignedPrinter=getPrinter();
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Jon in Printer %d during %d seconds\n",
                    Thread.currentThread().getName(),assignedPrinter,duration);
            freePrinters[assignedPrinter]=true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
    private int getPrinter(){
        int ret=-1;
        try {
            lockPrinters.lock();
            for(int i=0;i<freePrinters.length;i++){
                if(freePrinters[i]){
                    ret=i;
                    freePrinters[i]=false;
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            lockPrinters.unlock();
        }
        return ret;
    }
}
