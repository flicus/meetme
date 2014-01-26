package org.ffff.meetme.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by flicus on 26.01.14.
 */
public class Session {
    private long timestamp = System.currentTimeMillis();
    private Map<String, Object> items = new HashMap<>();

    public Session() {
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object get(String key) {
        return items.get(key);
    }

    public Object set(String key, Object obj) {
        return items.put(key, obj);
    }

    public Object remove(String key) {
        return items.remove(key);
    }

    public void clear() {
        items.clear();
    }

}
