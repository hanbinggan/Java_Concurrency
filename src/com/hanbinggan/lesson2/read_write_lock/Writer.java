package com.hanbinggan.lesson2.read_write_lock;

/**
 * Created by hello on 2016/4/23.
 */
public class Writer implements Runnable {
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random()*10,Math.random()*10);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
