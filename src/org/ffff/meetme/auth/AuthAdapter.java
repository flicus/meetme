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
 * Time: 18:23
 * <p/>
 * $Id:
 */

public interface AuthAdapter {

    public String getAppId();

    public String getOAuthURL();

    public boolean login();

    public List<Contact> getContacts();
}