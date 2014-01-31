package org.ffff.meetme.model.yandex;

/**
 * Created by flicus on 27.01.14.
 */
public class Location {
    private String id;
    private String name;

    public Location() {
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
