package com.hanbinggan.runMd5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hello on 2016/4/17.
 */
public class Md5Cal implements Runnable{
    private static int curNum=101;
    private static final  int max=1002;
    public static int ans[]=new int[max+1];
    static String convert(String str) throws NoSuchAlgorithmException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        byte[]ans=md5.digest(str.getBytes());
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<ans.length;i++){
            sb.append(Integer.toString((ans[i]&0xff)+0x100,16).substring(1));
        }
        return sb.toString();
    }
    @Override
    public void run() {
        while(curNum<max){
            int num=curNum++;
            String str="20160417hanbinggan"+num;
            int j=0;
            while(true){
                String tmp= null;
                try {
                    tmp = convert(str+j);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if(tmp.startsWith("000000")){
                    ans[num]=j;
                    break;
                }
                j++;
            }
        }
    }
}
