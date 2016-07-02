package com.hanbinggan.lesson7.executor_threadFactory;

import java.util.Date;

public class MyThread extends Thread {
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    private long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName() + ": Creation Date: " + creationDate + " Running Time: " + getExecutionTime() + " Milliseconds");
        System.out.printf(builder.toString());
        return builder.toString();
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }

    public void setStartDate() {
        this.startDate = new Date();
    }

    public void setFinishDate() {
        this.finishDate = new Date();
    }
}
