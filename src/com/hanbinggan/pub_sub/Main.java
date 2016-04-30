package com.hanbinggan.pub_sub;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hello on 2016/4/25.
 */
public class Main {
    public static void main(){
        Map params=new LinkedHashMap<>();
        params.put("id",new Object());
        Publisher.instance().publish("",params);
    }
}
