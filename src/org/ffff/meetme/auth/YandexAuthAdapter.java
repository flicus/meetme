package org.ffff.meetme.auth;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.ffff.meetme.model.AdapterInfo;
import org.ffff.meetme.model.Contact;
import org.ffff.meetme.model.yandex.GetTokenRes;
import org.ffff.meetme.model.yandex.Person;
import org.ffff.meetme.model.yandex.UserInfoRes;

import java.io.IOException;
import java.util.ArrayList;
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

public class YandexAuthAdapter extends AuthAdapter {

    public static AdapterInfo adapterInfo =
            new AdapterInfo(
                    "http://api.yandex.ru/login/doc/dg/images/login-with-yandex-6.png",
                    "dd6fd59ec1224fb4990daaf788d01b67",
                    "64abbb5dd0204e6ba43de383c5405692",
                    "https://oauth.yandex.ru/authorize?response_type=code&client_id=dd6fd59ec1224fb4990daaf788d01b67",
                    "https://oauth.yandex.ru/token",
                    "Yandex",
                    YandexAuthAdapter.class,
                    "https://login.yandex.ru/info?format=json&oauth_token=",
                    "http://api.moikrug.ru/v1/my/friends?format=json&oauth_token=",
                    "http://api.moikrug.ru/v1/person?ids=");


    //53f5fe4f65e24e5a821c8f0a1142c7eb

    public AdapterInfo getAdapterInfo() {
        return adapterInfo;
    }

    public YandexAuthAdapter() {
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public Contact getUserInfo() {
        Contact contact = null;
        HttpGet get = new HttpGet(adapterInfo.getUserInfoUrl()+token);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            UserInfoRes userInfoRes = gson.fromJson(res, UserInfoRes.class);
            contact = new Contact(userInfoRes.getDisplay_name(), userInfoRes.getDefault_email());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response!=null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contact;
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        HttpGet get = new HttpGet(adapterInfo.getContactsUrl()+token);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            int[] ids = gson.fromJson(res, int[].class);
            StringBuilder sb = new StringBuilder(adapterInfo.getContactsInfoUrl());
            for (int i = 0;i<=ids.length;i++) {
                sb.append(ids[i]);
                if (i<ids.length)sb.append(",");
            }
            sb.append("&oauth_token=").append(token);
            HttpGet g = new HttpGet(sb.toString());
            CloseableHttpResponse r = httpclient.execute(g);
            String _persons = EntityUtils.toString(response.getEntity());
            Person[] persons = gson.fromJson(_persons, Person[].class);
            for (Person person : persons) {
                Contact contact = new Contact(person.getName(), "");
                contacts.add(contact);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response!=null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }

    @Override
    public boolean doStep2(String code) {
        boolean result = false;
        HttpPost post = new HttpPost(adapterInfo.getOuathUrlStep2());
        StringEntity entity = new StringEntity(
                String.format("grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s", code, adapterInfo.getAppId(), adapterInfo.getAppSecret()),
                ContentType.APPLICATION_FORM_URLENCODED);
        post.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                GetTokenRes getTokenRes = gson.fromJson(res, GetTokenRes.class);
                if (getTokenRes.getError() == null) {
                    token = getTokenRes.getAccess_token();
                    result = true;
                } else {
                    error = getTokenRes.getError();
                }
            } else {
                String res = EntityUtils.toString(response.getEntity());
                GetTokenRes getTokenRes = gson.fromJson(res, GetTokenRes.class);
                error = getTokenRes.getError()+" : "+getTokenRes.getError_description();
            }
        } catch (ClientProtocolException e) {
            error = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            error = e.getMessage();
            e.printStackTrace();
        } finally {
            if (response!=null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
