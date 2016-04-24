package com.hanbinggan.lesson3.cyclic_barrier;

import java.util.Random;

/**
 * Created by hello on 2016/4/24.
 */
public class MatrixMock {
    private int date[][];
    public MatrixMock(int size,int length,int number){
        int count=0;
        date=new int[size][length];
        Random random=new Random();
        for(int i=0;i<size;i++){
            for(int j=0;j<length;j++){
                date[i][j]=random.nextInt(10);
                if(date[i][j]==number){
                    count++;
                }
            }
        }
        System.out.printf("Mock: There are %d ocurrences of number %d in generated data.\n",count,number);
    }
    public int[] getRow(int row){
        if((row>=0)&&(row<date.length)){
            return date[row];
        }
        return null;
    }
}
