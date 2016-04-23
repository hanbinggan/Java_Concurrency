package com.hanbinggan.lesson2.multiple_condition;

/**
 * Created by hello on 2016/4/23.
 */
public class Producer implements Runnable {
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()){
            String line=mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
