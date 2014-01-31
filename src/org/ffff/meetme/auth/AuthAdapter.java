package org.ffff.meetme.auth;

import com.google.gson.Gson;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.ffff.meetme.model.AdapterInfo;
import org.ffff.meetme.model.Contact;

import java.util.List;

/**
 * Copyright (c) 2013 Amdocs jNetX.
 * http://www.amdocs.com
 * All rights reserved.
 * <p/>
 * This software is the confidential and proprietary information of
 * Amdocs jNetX. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with Amdocs jNetX.
 * <p/>
 * User: Sergey Skoptsov (sskoptsov@amdocs.com)
 * Date: 23.01.14
 * Time: 18:23
 * <p/>
 * $Id:
 */

public abstract class AuthAdapter {

    protected CloseableHttpClient httpclient = HttpClients.createDefault();
    protected String error = null;

    protected String token;
    protected Gson gson = new Gson();

    public String getError() {
        return error;
    }

    public abstract AdapterInfo getAdapterInfo();

    public abstract boolean login();

    public abstract Contact getUserInfo();

    public abstract List<Contact> getContacts();

    public abstract boolean doStep2(String code);
}
