package com.hanbinggan.lesson7.runnableScheduledFuture;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 周期性任务必须添加到执行器的队列作为新任务才能被再次执行
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    private RunnableScheduledFuture<V> task;
    private ScheduledThreadPoolExecutor executor;
    private long period;
    private long startDate;

    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        if (!isPeriodic()) {
            return task.getDelay(unit);
        } else {
//            System.out.printf("startDate: %s\n",new Date(startDate));
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                Date now = new Date();
                long delay = startDate - now.getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    @Override
    public void run() {
        if (isPeriodic() && (!executor.isShutdown())) {
            Date now = new Date();
            startDate = now.getTime() + period;
//            System.out.printf("set startTime: %s\n",new Date(startDate));
//            System.out.printf("period:%s  %s\n",period,TimeUnit.SECONDS.convert(period,TimeUnit.MILLISECONDS));
            executor.getQueue().add(this);
        }
        System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
        System.out.printf("MyScheduledTask: Is Periodic: %s\n", isPeriodic());
        super.runAndReset();
        System.out.printf("Post-MyScheduledTask: %s\n", new Date());
    }

    public void setPeriod(long period) {
//        System.out.printf("setPeriod : %d",period);
        this.period = period;
    }
}
