package com.hanbinggan.lesson6.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by hello on 2016/5/5.
 * ConcurrentLinkedDeque 提供了从其他列表中读取数据的方法
 * getFirst() getLast() 返回 第一个和 最后一个元素，不会移除，若列表 为 空则抛出 NoSuchElementException
 * peek() peekFirst()  peekLast() 分别返回 第一个 和 最后 一个 ， ，如果 列表为空 则 返回 null
 * remove()  removeFirst() removeLast()  分别返回第一个和最后一个元素并移除，如果为空 则跑输 NoSuchElementException异常
 */
public class PollTask implements Runnable {
    private ConcurrentLinkedDeque<String>list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for(int i=0;i<5000;i++){
            list.pollFirst();
            list.pollLast();
        }
    }
}
