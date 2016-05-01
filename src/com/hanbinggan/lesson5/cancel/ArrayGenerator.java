package com.hanbinggan.lesson5.cancel;

import java.util.Random;

/**
 * Created by hello on 2016/5/1.
 */
public class ArrayGenerator {
    public int[] generateArray(int size){
        int[] array = new int[size];
        Random random=new Random();
        for(int i=0;i<size;i++){
            array[i]=random.nextInt(100);
        }
        return array;
    }
}
