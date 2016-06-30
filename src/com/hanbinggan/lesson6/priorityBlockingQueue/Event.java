package com.hanbinggan.lesson6.priorityBlockingQueue;

public class Event implements Comparable<Event>{
    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        if(priority>o.getPriority()){
            return -1;
        }else if(priority<o.getPriority()){
            return 1;
        }else{
            return 0;
        }
    }
}
