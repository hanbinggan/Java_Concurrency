package com.hanbinggan.lesson2;

import com.hanbinggan.lesson2.multiple_condition.Buffer;
import com.hanbinggan.lesson2.multiple_condition.FileMock;
import com.hanbinggan.lesson2.printQueue.Job;
import com.hanbinggan.lesson2.printQueue.PrintQueue;
import com.hanbinggan.lesson2.producer_and_consumer.Consumer;
import com.hanbinggan.lesson2.producer_and_consumer.EventStorage;
import com.hanbinggan.lesson2.producer_and_consumer.Producer;
import com.hanbinggan.lesson2.read_write_lock.PricesInfo;
import com.hanbinggan.lesson2.read_write_lock.Reader;
import com.hanbinggan.lesson2.read_write_lock.Writer;

/**
 * Created by hello on 2016/4/23.
 */
public class Main {

    public static void main(String argc[]){
        /**
         * 银行账户两个线程
         * 使用 synchronized 实现同步方法
         * */
        /*Account account=new Account();
        account.setBalance(1000);

        Company company=new Company(account);
        Thread companyThread=new Thread(company);

        Bank bank=new Bank(account);
        Thread backThread=new Thread(bank);

        System.out.printf("Account : Initial Balance: %s\n",account.getBalance());

        companyThread.start();
        backThread.start();

        try {
            companyThread.join();
            backThread.join();
            System.out.printf("Account : Final Balance: %f\n",account.getBalance());
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/

        /**
         * 2.3 使用非依赖属性实现同步
         * */
       /* Cinema cinema=new Cinema();
        TicketOffice1 ticketOffice1=new TicketOffice1(cinema);
        Thread thread1=new Thread(ticketOffice1,"TicketOffice1");
        TicketOffice2 ticketOffice2=new TicketOffice2(cinema);
        Thread thread2=new Thread(ticketOffice2,"TicketOffice2");

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());*/

        /**
         * 2.4生产者消费者模型
         * */
       /* EventStorage storage=new EventStorage();

        Producer producer=new Producer(storage);
        Thread thread1=new Thread(producer);

        Consumer consumer=new Consumer(storage);
        Thread thread2=new Thread(consumer);

        thread1.start();
        thread2.start();*/

        /**
         * 2.5锁同步
         * */
       /* PrintQueue printQueue=new PrintQueue();

        Thread thread[]=new Thread[10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Job(printQueue),"Thread"+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
        }*/

        /**
         * 2.6 打印信息
         * 读写锁
         * */
        /*PricesInfo pricesInfo=new PricesInfo();
        Reader readers[]=new Reader[5];
        Thread readerThreads[]=new Thread[5];
        for(int i=0;i<5;i++){
            readers[i]=new Reader(pricesInfo);
            readerThreads[i]=new Thread(readers[i]);
        }

        Writer writer=new Writer(pricesInfo);
        Thread writerThread=new Thread(writer);

        for(int i=0;i<5;i++){
            readerThreads[i].start();
        }
        writerThread.start();*/

        /**
         * 2.7锁 公平性
         * */
       /*PrintQueue printQueue=new PrintQueue();

        Thread thread[]=new Thread[10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Job(printQueue),"Thread"+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }*/

        /**
         * 2.8
         * */
        FileMock mock=new FileMock(100,10);
        Buffer buffer=new Buffer(20);

        com.hanbinggan.lesson2.multiple_condition.Producer producer
                = new com.hanbinggan.lesson2.multiple_condition.Producer(mock,buffer);
        Thread producerThread=new Thread(producer,"Producer");

        com.hanbinggan.lesson2.multiple_condition.Consumer consumers[]
                =new com.hanbinggan.lesson2.multiple_condition.Consumer[3];
        Thread consumerThreads[]=new Thread[3];
        for(int i=0;i<3;i++){
            consumers[i]=new com.hanbinggan.lesson2.multiple_condition.Consumer(buffer);
            consumerThreads[i]=new Thread(consumers[i],"Consumer"+i);
        }
        producerThread.start();
        for(int i=0;i<3;i++){
            consumerThreads[i].start();
        }
    }
}
