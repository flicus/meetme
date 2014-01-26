package org.ffff.meetme;

import org.ffff.meetme.web.WebServer;

import java.io.IOException;

/**
 * Created by flicus on 26.01.14.
 */
public class Main {

    public static void main(String[] args) {


        WebServer webServer = null;

        try {
            webServer = new WebServer();
            MMExecutors.getInstance().getExecutor().execute(webServer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
