package com.hanbinggan.lesson6.atomic;
/**
 * 原子变量提供了单个变量上的原子操作
 * 当给一个变量赋值的时候，在Java代码中只使用一个指令，但是编译成不同的JVM语言
 * 当多个线程共享的时候可能发生数据不一致的错误
 *
 * 原子变量Atomic 当线程对一个变量进行操作，其他线程也操作
 * 操作先获取变量值，然后，在本地改变，然后用这个值跟原来的值替换
 * 如果原来的值没有被其他线程替换则进行替换
 * 如果被其他线程改变则重新执行该操作
 * CAS(compare and Set)
 * 原子变量，所有操作基于CAS，保证了多线程在同一时间操作一个原子变量不会产生数据不一致的错误
 * 操作步骤：
 *  1. 取得旧变量的值
 * 2. 在一个临时变量中修改变量值，即变量新值
 * 3. 如果获得变量的旧值和当前变量的值相同，则新值替换旧值。如果不相同则是其他线程改变了变量值，操作需要重做
 * 且性能高于使用同步机制（synchronized/Lock）保护的普通变量
 * 同步机制存在 死锁 / 必须执行获取锁和释放锁操作（及时只有一个线程访问共享对象）等问题
 * 比较和交换机制不需要使用同步机制，并且没有死锁，性能好
 * 原子变量实现了 compareAndSet() 方法，其他方法基于该方法展开
 * AtomicReference 可以进行对象的原子操作
 */

import java.util.concurrent.atomic.AtomicLong;

public class Account {
    private AtomicLong banlance;
    public Account(){
        banlance=new AtomicLong();
    }
    public long getBalance(){
        return banlance.get();
    }
    public void setBalance(long balance){
        this.banlance.set(balance);
    }
    public void addAmount(long amount){
        this.banlance.getAndAdd(amount);
    }
    public void subtractAmount(long amount){
        this.banlance.getAndAdd(-amount);
    }
}
