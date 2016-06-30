package com.hanbinggan.runMd5;

/**
 * Created by hello on 2016/5/3.
 */
public class Tmp {
    private static final int twist(int u, int v) {
        return (((u & 0x80000000) | (v & 0x7fffffff)) >> 1) ^ ((v & 1) == 1 ? 0x9908b0df : 0);
    }
    private int[] state= new int[624];
    private int left = 1;
    public void srand(int seed){
        if(seed==0){
            seed=1;
        }
        for(int j=0;j<624;j++){
            state[j]=(j+1)*seed;
        }
    }
    public void next_state() {
        int p = 0;
        left = 624;
        for (int j = 228; --j > 0; p++)
            state[p] = state[p+397] ^ twist(state[p], state[p + 1])^2074608327;

        for (int j=397;--j>0;p++)
            state[p] = state[p-227] ^ twist(state[p], state[p + 1])^2074608327;

        state[p] = state[p-227] ^ twist(state[p], state[0])^2074608327;
    }

    public int next() {
        if (--left == 0) next_state();
        return Math.abs(state[624-left]);
    }
    public static void main(String[] args) {
        int seed=10;
        Tmp rand=new Tmp();
        rand.srand(seed);
        int[]arr=new int[100];
        int ans=0;
    }
}
