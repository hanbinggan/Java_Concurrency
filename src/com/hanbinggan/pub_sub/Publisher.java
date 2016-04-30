package com.hanbinggan.pub_sub;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hello on 2016/4/25.
 */
public class Publisher {
    private static Publisher single;
    private static Map<String,ArrayList<Subscriber>> subscribers;

    private Publisher(){}

    public static Publisher instance(){
        if(single==null){
            single=new Publisher();
        }
        return single;
    }
    public void register(String message,Subscriber subscriber){
        if(subscriber==null){
            return;
        }
        if(subscribers==null){
            subscribers=new LinkedHashMap<String ,ArrayList<Subscriber>>();
        }
        ArrayList<Subscriber> subscriberList =subscribers.get(message);
        if(subscriberList==null){
            subscriberList=new ArrayList<Subscriber>();
        }
        subscriberList.add(subscriber);
    }
    public void publish(String message,Map params){
        if(subscribers==null){
            return;
        }
        ArrayList<Subscriber> subscriberList=subscribers.get(message);
        if(subscriberList==null||subscriberList.isEmpty()){
            return;
        }
        for(Subscriber sub:subscriberList){
            sub.onRecived(message,params);
        }
    }
}
