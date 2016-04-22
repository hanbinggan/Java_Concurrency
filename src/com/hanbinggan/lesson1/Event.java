package com.hanbinggan.lesson1;

import java.util.Date;

/**
 * Created by hello on 2016/4/21.
 */
public class Event {
    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }
}
