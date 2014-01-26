package org.ffff.meetme.model;

/**
 * Created by flicus on 26.01.14.
 */
public class AdapterInfo {
    private String loginButtonUrl;
    private String appId;
    private String ouathUrl;
    private String name;

    public AdapterInfo() {
    }

    public AdapterInfo(String loginButtonUrl, String appId, String ouathUrl, String name) {
        this.loginButtonUrl = loginButtonUrl;
        this.appId = appId;
        this.ouathUrl = ouathUrl;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdapterInfo{" +
                "loginButtonUrl='" + loginButtonUrl + '\'' +
                ", appId='" + appId + '\'' +
                ", ouathUrl='" + ouathUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOuathUrl() {
        return ouathUrl;
    }

    public void setOuathUrl(String ouathUrl) {
        this.ouathUrl = ouathUrl;
    }

}
