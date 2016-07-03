package com.hanbinggan.lesson8.lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {
    public String getOwnerName() {
        if (this.getOwner() == null) {
            return "None";
        } else {
            return this.getOwner().getName();
        }
    }

    public Collection<Thread> getThreads() {
        return this.getQueuedThreads();
    }
}
