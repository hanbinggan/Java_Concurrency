package com.hanbinggan.lesson3.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hello on 2016/4/24.
 * CyclicBarrier 类 有一个内部计数器，可以控制指定数目的几个线程必须都到达集合点。
 * 每一个线程到达集合后就会调用await()方法通知 CyclicBarrier 对象，
 * CyclicBarrier对象会让这个线程休眠直到其他所有线程到达集合点
 * CyclicBarrier 类还提供 await(long time,TimeUnit unit)方法，线程将休眠到 1.被中断 2.计数器为0 3.指定时间过期
 * getNumberWaiting()方法：阻塞的线程数目
 * getParties() 方法 ，被CyclicBarrier 对象的同步数目
 * CyclicBarrier 可以被重置回 初始状态 并把它的内部计数器重置为初始化的值。通过 reset() 方法完成
 * CyclicBarrier 对象  很多线程在等待，其中一个线程 抛出 中断异常 则 其他等待的线程 抛出 BrokenBarrierException 异常
 * 此时 CyclicBarrier 对象处于 中断状态
 * CyclicBarrier 类 提供了 isBroken() 方法 返回是否处于损坏状态
 */
public class Searcher implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Result result;
    private int number;
    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Result result, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.result = result;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
        for(int i=firstRow;i<lastRow;i++){
            int row[]=mock.getRow(i);
            counter=0;
            for(int j=0;j<row.length;j++){
                if(row[j]==number){
                    counter++;
                }
            }
            result.setData(i,counter);
        }
        System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());
        try {
            barrier.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
