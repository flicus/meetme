package org.ffff.meetme.model;

/**
 * Created by flicus on 27.01.14.
 */
public class Place {
    private String id;
    private String name;
    private Contact owner;

    public Place(String id, String name, Contact owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Place() {
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
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

    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }
}
