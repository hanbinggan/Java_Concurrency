package com.hanbinggan.lesson1;

/**
 * Created by hello on 2016/4/20.
 */
public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number=2L;
        while(true){
            if(isPrime(number)){
                System.out.printf("Number %d is Prime\n",number);
            }
            if(isInterrupted()){
                System.out.printf("Prime Generator has been Interrupted.\n");
                return;
            }
            number++;
        }
    }
    private boolean isPrime(long number){
        if(number==2){
            return true;
        }
        for(long i=2;i<number;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}
