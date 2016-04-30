package com.hanbinggan.lesson4.completionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/30.
 * 获取到结果
 * 调用poll() 方法访问 完成服务的任务队列，如果有，则返回队列中第一个元素
 * 第二个参数为返回时间
 *
 * CompletionService 类可以执行 Callable 或 Runnable 类型的任务
 *两种方法获取结果 poll() 用于检查队列中是否有 Future对象 ，如果队列为 空 返回 null
 * take() 检查队列中是否有Future元素，如果没有则阻塞线程直到队列中有可用元素，返回第一个元素并移除这个元素
 */
public class ReportProcessor implements Runnable {
    private CompletionService<String>service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        end=false;
    }

    @Override
    public void run() {
        while(!end){
            try {
                //获取已完成任务的Future 对象
                Future<String>result=service.poll(20, TimeUnit.SECONDS);
                if(result!=null){
                    String report=result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n",report);
                }
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender: End.\n");
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
