package org.ffff.meetme.auth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flicus on 26.01.14.
 */
public class AuthAdapterManager {

    private Map<String, AuthAdapter> adapters = new HashMap<String, AuthAdapter>();

    {
        adapters.put("yandex", new YandexAuthAdapter());

    }

    private AuthAdapterManager() {

    }

    public Collection<AuthAdapter> getAdapters() {
        return adapters.values();
    }

    public AuthAdapter getAdapter(String id) {
        return adapters.get(id);
    }


    private final static class Singleton {
        final static AuthAdapterManager instanse = new AuthAdapterManager();
    }

    public static AuthAdapterManager getInstance() {
        return Singleton.instanse;
    }

}
