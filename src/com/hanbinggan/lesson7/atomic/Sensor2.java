package com.hanbinggan.lesson7.atomic;

public class Sensor2 implements Runnable {
    private ParkingCounter counter;

    public Sensor2(ParkingCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.callIn();
        counter.callOut();
        counter.callOut();
        counter.callIn();
        counter.callIn();
        counter.callIn();
        counter.callIn();
        counter.callIn();
        counter.callIn();
    }
}
