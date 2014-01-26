package org.ffff.meetme;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;

public class MMExecutors {

    private ScheduledExecutorService scheduler;
    private ExecutorService executor;

    public MMExecutors() {

        scheduler = Executors.newScheduledThreadPool(3);
        executor = Executors.newCachedThreadPool();
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    private static class Singleton {
        public static MMExecutors instance = new MMExecutors();
    }

    public static MMExecutors getInstance() {
        return Singleton.instance;
    }
}
