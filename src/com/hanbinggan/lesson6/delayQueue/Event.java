package com.hanbinggan.lesson6.delayQueue;
/**
 * 继承Delay接口才可以存放到DelayQueue队列中
 * getDelay()方法用来激活日期和实际日期之间之间的纳秒数
 * 并使用TimeUnit 的convert()方法将时间间隔转化为event激活时间的剩余纳秒数
 */
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed{
    private Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Date now=new Date();
        long diff=startDate.getTime()-now.getTime();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long result=this.getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        if(result<0){
            return -1;
        }else if(result > 0){
            return 1;
        }
        return 0;
    }
}
