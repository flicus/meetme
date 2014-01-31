package org.ffff.meetme.model.yandex;

/**
 * Created by flicus on 27.01.14.
 */
public class Person {
    private String id;
    private String link;
    private String name;
    private String resume_headline;
    private boolean is_public;
    private String gender;
    private Avatar avatar;
    private Location location;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", resume_headline='" + resume_headline + '\'' +
                ", is_public=" + is_public +
                ", gender='" + gender + '\'' +
                ", avatar=" + avatar +
                ", location=" + location +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResume_headline() {
        return resume_headline;
    }

    public void setResume_headline(String resume_headline) {
        this.resume_headline = resume_headline;
    }

    public boolean isIs_public() {
        return is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
