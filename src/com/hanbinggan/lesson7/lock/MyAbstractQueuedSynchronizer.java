package com.hanbinggan.lesson7.lock;
/**
 * AbstractQueuedSynchronizer 类用来实现带有锁或信号特性的同步机制
 * tryAcquire() 当访问临界区代码调用该函数，访问成功返回 true
 * tryRelease() 放释放对缓冲区代码的访问调用该函数，释放成功返回 true
 */

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
    private AtomicInteger state;

    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}
