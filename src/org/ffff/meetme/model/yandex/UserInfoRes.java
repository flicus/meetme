package org.ffff.meetme.model.yandex;

/**
 * Created by flicus on 27.01.14.
 */
public class UserInfoRes {
    private String birthday;
    private String display_name;
    private String id;
    private String sex;
    private String[] emails;
    private String default_email;
    private String real_name;

    public UserInfoRes() {
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public String getDefault_email() {
        return default_email;
    }

    public void setDefault_email(String default_email) {
        this.default_email = default_email;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }
}
