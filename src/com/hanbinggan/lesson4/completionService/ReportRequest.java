package com.hanbinggan.lesson4.completionService;

import java.util.concurrent.CompletionService;

/**
 * Created by hello on 2016/4/30.
 * 模拟请求报告
 */
public class ReportRequest implements Runnable {
    private String name;
    private CompletionService<String > service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator=new ReportGenerator(name,"Report");
        service.submit(reportGenerator);
    }
}
