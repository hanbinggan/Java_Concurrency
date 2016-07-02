package com.hanbinggan.lesson7.priorityBlockingQueue;

public class Event implements Comparable<Event> {
    private String thread;
    private int priority;

    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        if (priority < o.getPriority()) {
            return 1;
        } else if (priority > o.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
}
