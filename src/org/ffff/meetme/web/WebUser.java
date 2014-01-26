package org.ffff.meetme.web;

/**
 * Created by flicus on 26.01.14.
 */
public class WebUser {
    private boolean loggedIn = false;

    public WebUser() {
    }

    public WebUser(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "loggedIn='" + loggedIn + '\'' +
                '}';
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
