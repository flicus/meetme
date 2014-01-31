package org.ffff.meetme.auth;

import org.ffff.meetme.model.AdapterInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flicus on 26.01.14.
 */
public class AuthAdapterManager {

    private Map<String, AdapterInfo> adapters = new HashMap();

    {
        adapters.put("yandex", YandexAuthAdapter.adapterInfo);
        adapters.put("vk", VKAuthAdapter.adapterInfo);

    }

    private AuthAdapterManager() {

    }

    public Collection<AdapterInfo> getAdapterInfos() {
        return adapters.values();
    }

    public AdapterInfo getAdapterInfo(String id) {
        return adapters.get(id);
    }

    public AuthAdapter makeAuthAdapter(String providerId) {
        Class clazz = adapters.get(providerId).getAdapterClass();
        AuthAdapter obj = null;
        try {
            obj = (AuthAdapter)clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private final static class Singleton {
        final static AuthAdapterManager instanse = new AuthAdapterManager();
    }

    public static AuthAdapterManager getInstance() {
        return Singleton.instanse;
    }

}
