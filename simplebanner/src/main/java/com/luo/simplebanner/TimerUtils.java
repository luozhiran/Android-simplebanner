package com.luo.simplebanner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LawnLuo on 2/15/17.
 */

public class TimerUtils {
    private Map<String,Long> cache;
    private static TimerUtils instance = null;

    private TimerUtils() {
        this.cache = new HashMap<String,Long>();
    }
    public void Timer(String name){
        cache.put(name,System.currentTimeMillis());
    }

    public long diff(String t1,String t2){
        if (cache!=null){
            long tl1 = cache.get(t1);
            long tl2 = cache.get(t2);
            return Math.abs(tl2-tl1);
        }
        return 0;
    }

    public static TimerUtils getInstance(){
        if (instance == null){
            synchronized (TimerUtils.class){
                if (instance == null){
                    return instance = new TimerUtils();
                }
            }
        }
        return instance;
    }
}
