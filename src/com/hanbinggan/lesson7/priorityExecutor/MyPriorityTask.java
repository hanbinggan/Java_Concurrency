package com.hanbinggan.lesson7.priorityExecutor;

import java.util.concurrent.TimeUnit;

public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
    private int priority;
    private String name;

    public MyPriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (priority < o.getPriority()) {
            return 1;
        } else if (priority < o.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s Priority: %d\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
