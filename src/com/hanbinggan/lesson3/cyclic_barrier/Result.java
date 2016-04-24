package com.hanbinggan.lesson3.cyclic_barrier;

/**
 * Created by hello on 2016/4/24.
 */
public class Result {
    private int data[];
    public Result(int size){
        data=new int[size];
    }
    public void setData(int position,int value){
        data[position]=value;
    }
    public int[] getData(){
        return data;
    }
}
