package org.ffff.meetme;

/**
 * Created by flicus on 26.01.14.
 */
public class Configuration {

    private String webRoot = "d:\\java\\git\\meetme\\web";
    private int port = 8080;

    public int getPort() {
        return port;
    }

    public String getWebRoot() {
        return webRoot;
    }

    private Configuration() {

    }

    private static final class Singleton {
        final static Configuration singleton = new Configuration();
    }

    public static Configuration getInstanse() {
        return Singleton.singleton;
    }
}
