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

    public Publisher(){}
    public void register(String message,Subscriber subscriber){
        if(subscriber==null){
            return;
        }
        if(subscribers==null){
            subscribers=new LinkedHashMap<>();
        }
        ArrayList<Subscriber> subscriberList =subscribers.get(message);
        if(subscriberList==null){
            subscriberList=new ArrayList<>();
        }
        subscriberList.add(subscriber);
    }
    public void unsubscribe(String message,Subscriber subscriber){
        if(subscriber==null){
            return;
        }
        ArrayList <Subscriber>subscriberList=subscribers.get(message);
        if(subscribers==null){
            return;
        }
        for(int i=0;i<subscriberList.size();i++){
            Subscriber s=subscriberList.get(i);
            if (s.equals(subscriber)){
                subscriberList.remove(i);
                break;
            }
        }
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
