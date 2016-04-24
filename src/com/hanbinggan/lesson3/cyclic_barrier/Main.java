package com.hanbinggan.lesson3.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hello on 2016/4/24.
 */
public class Main {
    public static void main(String args[]){
        final int ROWS=10000;
        final int NUMBERS=10000;
        final int SEARCH=5;
        final int PARTICIPANTS=5;
        final int LINES_PARTICIPANT=2000;
        MatrixMock mock=new MatrixMock(ROWS,NUMBERS,SEARCH);
        Result result=new Result(ROWS);
        Grouper grouper=new Grouper(result);
        //等待 5 个线程结束创建 grouper
        CyclicBarrier barrier=new CyclicBarrier(PARTICIPANTS,grouper);
        Searcher searchers[]=new Searcher[PARTICIPANTS];
        for(int i=0;i<PARTICIPANTS;i++){
            searchers[i]=new Searcher(i*LINES_PARTICIPANT,(i+1)*LINES_PARTICIPANT
                    ,mock,result,SEARCH,barrier);
            Thread thread=new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
