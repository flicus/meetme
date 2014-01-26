package org.ffff.meetme.web;

import org.ffff.meetme.MMExecutors;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SessionManager {

    private Map<String, Session> tokens = new HashMap<>();

    public SessionManager() {

        MMExecutors.getInstance().getScheduler().scheduleAtFixedRate(new ClearTokensTask(), 1, 2, TimeUnit.HOURS);

    }

    public String makeNewToken() {
        Session session = new Session();
        String token = UUID.randomUUID().toString();
        tokens.put(token, session);
        return token;
    }

    public Session checkToken(String token) {
        Session session = tokens.get(token);
        if (session != null) session.setTimestamp(System.currentTimeMillis());
        return tokens.get(token);
    }

    public class ClearTokensTask implements Runnable {

        @Override
        public void run() {
            try {
                List<String> toRemove = new ArrayList<>();

                long now = System.currentTimeMillis();
                for (Map.Entry<String, Session> entry : tokens.entrySet()) {
                    if ((now - entry.getValue().getTimestamp()) > 1000 * 60 * 60 * 24) toRemove.add(entry.getKey());    //24 hours
                }
                for (String key : toRemove) {
                    tokens.remove(key);
                }
            } catch (Exception e) {

            }
        }
    }

    private static class Singleton {
        public static final SessionManager instance = new SessionManager();
    }

    public static SessionManager getInstance() {
        return Singleton.instance;
    }
}

