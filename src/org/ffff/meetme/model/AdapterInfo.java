package org.ffff.meetme.model;

/**
 * Created by flicus on 26.01.14.
 */
public class AdapterInfo {
    private String loginButtonUrl;
    private String appId;
    private transient String appSecret;
    private String ouathUrlStep1;
    private transient String ouathUrlStep2;
    private String name;
    private transient Class adapterClass;
    private transient String userInfoUrl;
    private transient String contactsUrl;
    private transient String contactsInfoUrl;

    public AdapterInfo() {
    }

    public AdapterInfo(String loginButtonUrl, String appId, String appSecret, String ouathUrlStep1, String ouathUrlStep2, String name, Class adapterClass, String userInfoUrl, String contactsUrl, String contactsInfoUrl) {
        this.loginButtonUrl = loginButtonUrl;
        this.appId = appId;
        this.appSecret = appSecret;
        this.ouathUrlStep1 = ouathUrlStep1;
        this.ouathUrlStep2 = ouathUrlStep2;
        this.name = name;
        this.adapterClass = adapterClass;
        this.userInfoUrl = userInfoUrl;
        this.contactsUrl = contactsUrl;
        this.contactsInfoUrl = contactsInfoUrl;
    }

    @Override
    public String toString() {
        return "AdapterInfo{" +
                "loginButtonUrl='" + loginButtonUrl + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", ouathUrlStep1='" + ouathUrlStep1 + '\'' +
                ", ouathUrlStep2='" + ouathUrlStep2 + '\'' +
                ", name='" + name + '\'' +
                ", adapterClass=" + adapterClass +
                ", userInfoUrl='" + userInfoUrl + '\'' +
                ", contactsUrl='" + contactsUrl + '\'' +
                ", contactsInfoUrl='" + contactsInfoUrl + '\'' +
                '}';
    }

    public String getContactsInfoUrl() {
        return contactsInfoUrl;
    }

    public void setContactsInfoUrl(String contactsInfoUrl) {
        this.contactsInfoUrl = contactsInfoUrl;
    }

    public String getContactsUrl() {
        return contactsUrl;
    }

    public void setContactsUrl(String contactsUrl) {
        this.contactsUrl = contactsUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Class getAdapterClass() {
        return adapterClass;
    }

    public void setAdapterClass(Class adapterClass) {
        this.adapterClass = adapterClass;
    }

    public String getLoginButtonUrl() {
        return loginButtonUrl;
    }

    public void setLoginButtonUrl(String loginButtonUrl) {
        this.loginButtonUrl = loginButtonUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOuathUrlStep1() {
        return ouathUrlStep1;
    }

    public void setOuathUrlStep1(String ouathUrlStep1) {
        this.ouathUrlStep1 = ouathUrlStep1;
    }

    public String getOuathUrlStep2() {
        return ouathUrlStep2;
    }

    public void setOuathUrlStep2(String ouathUrlStep2) {
        this.ouathUrlStep2 = ouathUrlStep2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}