package org.ffff.meetme.model.yandex;

/**
 * Created by flicus on 27.01.14.
 */
public class GetTokenRes {
    private String access_token;
    private int expires_in;
    private String error;
    private String error_description;

    public GetTokenRes() {
    }

    @Override
    public String toString() {
        return "GetTokenRes{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
