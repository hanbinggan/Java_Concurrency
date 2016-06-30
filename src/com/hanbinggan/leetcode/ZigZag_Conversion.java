package com.hanbinggan.leetcode;
import java.util.ArrayList;
import java.util.List;

public class ZigZag_Conversion {


    public static class Solution {
        public String convert(String s, int numRows) {
            List<StringBuilder>builders=new ArrayList<>(numRows);
            for(int i=0;i<numRows;i++){
                StringBuilder builder=new StringBuilder();
                builders.add(builder);
            }
            int cnt=0;
            int cur=0;
            int dir=1;
            while (cnt<s.length()){
                builders.get(cur)
                        .append(s.charAt(cnt++));
                cur += dir;
                if(cur==numRows){
                    cur-=2;
                    if(cur<0){
                        cur=0;
                    }
                    dir=-1;
                }else if(cur == -1){
                    cur=1;
                    if(cur>numRows-1){
                        cur=numRows-1;
                    }
                    dir=1;
                }
            }
            StringBuilder ans=new StringBuilder();
            for(int i=0;i<numRows;i++){
//                System.out.printf("%s\n",builders.get(i).toString());
                ans.append(builders.get(i));
            }
            return ans.toString();
        }
    }
    public static void main(String[] args){
        Solution solution=new Solution();
        String ans=solution.convert("ABC", 1);
        System.out.printf("%s\n",ans);
    }
}
