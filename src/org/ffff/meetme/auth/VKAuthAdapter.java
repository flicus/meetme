package org.ffff.meetme.auth;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.ffff.meetme.model.AdapterInfo;
import org.ffff.meetme.model.Contact;
import org.ffff.meetme.model.vk.UserInfoRes;
import org.ffff.meetme.model.yandex.GetTokenRes;

import java.io.IOException;
import java.util.List;

/**
 * Created by flicus on 27.01.14.
 */
public class VKAuthAdapter extends AuthAdapter {

    public static AdapterInfo adapterInfo =
            new AdapterInfo(
                    "http://vk.com/images/vk32.png",
                    "4147316",
                    "205SsoZI3P460lNhj7Ct",
                    "https://oauth.vk.com/authorize?client_id=4147316&scope=offline&redirect_uri=http://0xffff.net/meetme/api/callback/vk/step1&response_type=code&v=5.7",
                    "https://oauth.vk.com/access_token?client_id=4147316&client_secret=205SsoZI3P460lNhj7Ct&redirect_uri=http://0xffff.net/meetme/api/callback/vk/step1&code=",
                    "ВКонтакте",
                    VKAuthAdapter.class,
                    "https://api.vk.com/method/users.get?v=5.7&access_token=",
                    "http://api.moikrug.ru/v1/my/friends?format=json&oauth_token=",
                    "http://api.moikrug.ru/v1/person?ids=");

    @Override
    public AdapterInfo getAdapterInfo() {
        return adapterInfo;
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
            contact = new Contact(userInfoRes.getFirst_name()+" "+userInfoRes.getLast_name(), "");
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
        return null;
    }

    @Override
    public boolean doStep2(String code) {
        boolean result = false;
        HttpGet get = new HttpGet(adapterInfo.getOuathUrlStep2()+code);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(get);
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
