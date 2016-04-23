package com.hanbinggan.lesson2.multiple_condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hello on 2016/4/23.
 * 一个锁可以关联一个或多个条件，这些条件通过Condition接口声明
 * 目的是 允许线程获取锁并且查看等待的某一个条件是否满足，不满足就挂起直到某个线程唤起
 * 与锁绑定的所有条件对象都是通过Lock接口声明的newCondition()方法创建的
 * 在使用条件时，必须获得这个条件绑定的锁，所以带条件的代码必须在 调用Lock对象 lock() 和 unlock()方法之间
 * 当线程调用条件 await()方法时，它将自动释放这个条件绑定的锁，其他线程可以访问这个锁，并对它执行操作
 * await(long time,TimeUnit unit) 1.其他线程中断 2.signal() , signalAll() 3.等待时间过去
 * awaitUninterruptibly() 不可被中断
 * awaitUntil(Date date)  1.中断2.signal3.最后期限到了
 * 可以与 ReadLock , WriteLock 一起使用
 */
public class Buffer {
    private LinkedList<String>buffer;
    private  int maxSize;
    private ReentrantLock lock;
    private Condition lines;//是否有可读的数据
    private Condition space;//是否有空余的地方
    private boolean pendingLines;

    public Buffer(int maxSize){
        this.maxSize=maxSize;
        buffer=new LinkedList<>();
        lock=new ReentrantLock();
        lines=lock.newCondition();
        space=lock.newCondition();
        pendingLines=true;
    }

    public void insert(String line){
        lock.lock();
        try {
            while (buffer.size()==maxSize){//等待空闲
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n",Thread.currentThread().getName(),buffer.size());
            lines.signalAll();//提醒有可读的数据
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public String get(){
        String line=null;
        lock.lock();
        try {
            while(buffer.size()==0&&hasPendingLines()){
                lines.await();//等待可读数据
            }
            if(hasPendingLines()){
                line=buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());
            }
            space.signalAll();//提醒有空地方
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines(){
        return pendingLines||buffer.size()>0;
    }
}
