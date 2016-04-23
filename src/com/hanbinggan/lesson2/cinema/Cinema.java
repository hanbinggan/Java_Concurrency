package com.hanbinggan.lesson2.cinema;

/**
 * Created by hello on 2016/4/23.
 * 用synchronized 关键字保护代码块时，我们使用对象作为它的传入参数，JVM保证同一时间只能有一个线程能够访问这个对象的代码保护块
 */
public class Cinema {
    private long vacanciesCinema1;
    private long vacanciesCinema2;

    private final Object controlCinema1,controlCinema2;

    public Cinema(){
        controlCinema1=new Object();
        controlCinema2=new Object();
        vacanciesCinema1=20;
        vacanciesCinema2=20;
    }
    public boolean sellTickets1(int number){
        synchronized (controlCinema1){
            if(number<vacanciesCinema1){
                vacanciesCinema1-=number;
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean sellTickets2(int number){
        synchronized (controlCinema2){
            if(number<vacanciesCinema2){
                vacanciesCinema2 -= number;
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean returnTickets1(int number){
        synchronized (controlCinema1){
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTickets2(int number){
        synchronized (controlCinema2){
            vacanciesCinema2 += number;
            return true;
        }
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }
}
