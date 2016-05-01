package com.hanbinggan.lesson5.createForkJoinPool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by hello on 2016/5/1.
 */
public class Task extends RecursiveAction {
    private static final long serialVersionUID=1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(last-first<10){
            updatePrices();
        }else{
            int middle=(last+first)/2;
            System.out.printf("Task: Pending tasks: %s\n",getQueuedTaskCount());
            Task t1=new Task(products,first,middle+1,increment);
            Task t2=new Task(products,middle+1,last,increment);
            /**
             * 执行一个主任务所创建的多个子任务
             * 当一个主任务等待它的子任务时
             * 执行这个主任务的工作者线程接收另一个等待执行的任务并执行
             * 因此 Fork/Join 框架比 Runnable 和 Callable 对象更高效的任务管理机制
             * */
            invokeAll(t1,t2);
        }
    }
    private void updatePrices(){
        for(int i=first;i<last;i++){
            Product product=products.get(i);
            product.setPrice(product.getPrice()*(1+increment));
        }
    }
}
