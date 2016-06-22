package org.ffff.meetme.auth;

import org.ffff.meetme.model.Contact;

import java.util.List;

public class YandexAuthAdapter implements AuthAdapter {
    @Override
    public String getAppId() {
        return "dd6fd59ec1224fb4990daaf788d01b67";
    }

    @Override
    public String getOAuthURL() {
        return String.format("https://oauth.yandex.ru/authorize?response_type=token&client_id=%s", getAppId());
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }
}
