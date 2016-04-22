package com.hanbinggan.lesson1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hello on 2016/4/20.
 */
public class DataSourcesLoader implements Runnable{
    @Override
    public void run() {
        System.out.printf("Beginning data sources loading:%s\n",new Date());
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Data Sources loading has been finished:%s\n",new Date());
    }
}
