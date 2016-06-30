package com.hanbinggan.leetcode;

public class String_to_Integer {
    public static class Solution {
        public int myAtoi(String str) {
            str=str.trim();
            int num=1;
            if(str.startsWith("+")){
                str=str.substring(1);
            }else if(str.startsWith("-")){
                num=-1;
                str=str.substring(1);
            }
            long ans=0;
            for(int i=0;i<str.length();i++){
                if(!Character.isDigit(str.charAt(i))){
                    break;
                }
                ans=Long.min(ans*10+Integer.valueOf(""+str.charAt(i)),((long) Integer.MAX_VALUE+1));
            }
            ans*=num;
            if(ans>Integer.MAX_VALUE){
                ans=Integer.MAX_VALUE;
            }
            if(ans<Integer.MIN_VALUE){
                ans=Integer.MIN_VALUE;
            }
            return (int)ans;
        }
    }
    public static void main(String[] args){
        Solution solution=new Solution();
        long x=solution.myAtoi("9223372036854775809");
        System.out.printf("%d\n",x);
    }
}
