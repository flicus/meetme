package org.ffff.meetme.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flicus on 27.01.14.
 */
public class Meeting {

    private String id;
    private String name;
    private String description;
    private Contact owner;
    private Collection<Contact> members =  new ArrayList<>();
    private Collection<Place> places = new ArrayList<>();
    private Place place;
    private Map<Contact, Collection<MeetingDate>> dates = new HashMap<>();

    public Meeting() {
    }

    public Meeting(String id, String name, String description, Contact owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        members.add(owner);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }

    public Collection<Contact> getMembers() {
        return members;
    }

    public void setMembers(Collection<Contact> members) {
        this.members = members;
    }

    public Collection<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<Place> places) {
        this.places = places;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Map<Contact, Collection<MeetingDate>> getDates() {
        return dates;
    }

    public void setDates(Map<Contact, Collection<MeetingDate>> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", members=" + members +
                ", places=" + places +
                ", place=" + place +
                ", dates=" + dates +
                '}';
    }
}
