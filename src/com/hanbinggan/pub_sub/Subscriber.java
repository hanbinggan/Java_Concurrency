package com.hanbinggan.pub_sub;

import java.util.Map;

/**
 * Created by hello on 2016/4/25.
 */
public interface Subscriber {
    public void onRecived(String message,Map params);
}
