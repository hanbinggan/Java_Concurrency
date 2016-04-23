package com.hanbinggan.lesson2.bank;

/**
 * Created by hello on 2016/4/23.
 * synchronized 关键字的使用，保证了在并发程序中队共享数据的正确访问
 * 一个对象的方法采用synchronized 关键字进行声明，只能被一个线程访问
 * 一个类中的某个对象有不同的两个同步方法 A()、B() ；如果一个线程访问A()方法，则其他要访问A()或B()方法的线程将被阻塞
 * 如果是其他对象的同步方法则不会被阻塞
 * synchronized 关键字会降低应用程序的性能，只能在并发情景中需要修改共享数据的方法上使用
 * 可以递归调用被 synchronized 声明的方法，当线程访问一个对象的同步方法时，它还可以调用这个对象的其他同步方法
 * 可以通过 synchronized 关键字保护代码块（临界区）
 * 格式：
 * synchronized (this){
 *     //Java code
 * }
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    //临界区
    public synchronized void addAmount(double amount){
        double tmp=balance;
        try {
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        tmp += amount;
        balance=tmp;
    }
    public  synchronized void subtractAmount(double amount){
        double tmp=balance;
        try {
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        tmp -= amount;
        balance=tmp;
    }
}
