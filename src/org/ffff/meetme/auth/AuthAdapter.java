package org.ffff.meetme.auth;

import org.ffff.meetme.model.Contact;

import java.util.List;

public interface AuthAdapter {

    public String getAppId();

    public String getOAuthURL();

    public boolean login();

    public List<Contact> getContacts();
}
