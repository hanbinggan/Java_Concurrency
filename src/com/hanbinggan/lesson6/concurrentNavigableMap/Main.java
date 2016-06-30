package com.hanbinggan.lesson6.concurrentNavigableMap;
/**
 * ConcurrentSkipListMap使用Skip List存放数据
 * 当插入元素到映射中 ConcurrentSkipListMap使用键值来排序所有元素
 * 使用 subMap()返回部分元素， 对象是 ConcurrentNavigableMap 是一个子映射
 * headMap(K toKey) tailMap(K fromKey) 返回前面或后面的元素类型同上
 * putIfAbsent(K keu,V value) 如果不存在key 则保存映射
 * pollLastEntry pollFirstEntry
 * replace(K key, V value)
 */

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args){
        ConcurrentSkipListMap<String,Contact>map=new ConcurrentSkipListMap<>();
        Thread[] threads=new Thread[25];
        int counter=0;
        for(char i='A';i<'Z';i++){
            Task task=new Task(map,String.valueOf(i));
            threads[counter]=new Thread(task);
            threads[counter].start();
            counter++;
        }
        for(int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Size of the map: %d\n",map.size());
        Map.Entry<String,Contact>element=map.lastEntry();
        Contact contact=element.getValue();
        System.out.printf("Main: Last Entry %s: %s\n",contact.getName(),contact.getPhone());
        System.out.printf("Main: Sub map from A1996 to B1002: \n");
        ConcurrentNavigableMap<String,Contact>subMap=map.subMap("A1996","B1002");
        do{
            element=subMap.pollFirstEntry();
            if(element!=null){
                contact=element.getValue();
                System.out.printf("%s: %s\n",contact.getName(),contact.getPhone());
            }
        }while (element !=null);
    }
}
