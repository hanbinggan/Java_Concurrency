package com.hanbinggan.lesson5.createForkJoinPool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/5/1.
 * ForkJoinTask 类 invokeAll() 方法是执行器框架
 * 线程池中包含了待执行方法的任务，任务的控制也是在线程池中进行
 * execute(RunnableTask) 方法发送一个Runnable 任务给 ForkJoinPool 类，在此情况下 ForkJoinPool 不采取 工作窃取算法
 * ForkJoinPool类 仅在使用ForkJoinTask 类时才会采用工作剽取算法
 * invoke(ForkJoinTask<T> task) : ForkJoinPool 类 的 execute()方法是异步调用，
 * invoke() 是同步调用，这个方法直到传递进来的任务执行结束后才会返回
 * 也可以使用 ExecutorService类中的声明 invokeAll() 和 invokeAny()方法 ，这些方法接收Callable 对象 作为参数
 * 此时 ForkJoinPool 类 将就不采取工作窃取算法
 *
 * invokeAll(ForkJoinTask<?>...tasks)  / invokeAll(Collection<ForkJoinTask>tasks)
 * ForkJoinPool 类 是 设计用来 执行 ForkJoinTask 对象但是可以用来执行 Runnable 和 Callable 对象
 * 也可以使用 ForkJoinTask  类的 adapt() 方法来接收一个 Callable对象 或者一个 Runnable 对象，
 * 然后将之转换为一个 ForkJoinTask对象 再执行
 */
public class Main {
    public static void main(String[] args){
        ProductListGenerator generator=new ProductListGenerator();
        List<Product> products=generator.generate(10000);
        Task task=new Task(products,0,products.size(),0.20);
        //创建一个线程数等于计算机CPU数目的线程池，创建好ForkJoinPool对象之后，线程也创建就绪等待任务到达，开始执行
        ForkJoinPool pool=new ForkJoinPool();
        pool.execute(task);

        do{

            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (!task.isDone());
        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.printf("Main: The process has completed normally.\n");
        }
        for(int i=0;i<products.size();i++){
            Product product=products.get(i);
            if(product.getPrice() != 12){
                System.out.printf("Product %s: %s\n",product.getName(),product.getPrice());
            }
        }
        System.out.printf("Main: End of the program.\n");
    }
}
