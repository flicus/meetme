package org.ffff.meetme.auth;

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
 * Time: 18:25
 * <p/>
 * $Id:
 */

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
