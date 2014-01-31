package org.ffff.meetme.dao;

import org.ffff.meetme.model.Contact;
import org.ffff.meetme.model.Meeting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flicus on 27.01.14.
 */
public class TestDAO implements DAOInterface {

    private static final Map<String, Meeting> meetings = new HashMap<>();
    private static final Map<String, Contact> contacts = new HashMap<>();

    static {
        Contact c = new Contact("Фликус", "flicus@ya.ru");
        contacts.put(c.getEmail(), c);

        Meeting m = new Meeting("11", "Бухашка", "Давно плять не бухали епт", c);
        meetings.put(m.getId(), m);

    }

    @Override
    public Collection<Meeting> getMeetings(String email) {
        Contact c = contacts.get(email);
        return getMeetings(c);
    }

    @Override
    public Collection<Meeting> getMeetings(Contact member) {
        Collection<Meeting> list = new ArrayList<>();
        for (Meeting m : meetings.values()) {
            if (m.getMembers().contains(member)) list.add(m);
        }
        return list;
    }

    @Override
    public Meeting addMeeting(Meeting meeting) {
        return meetings.put(meeting.getId(), meeting);
    }

    @Override
    public Meeting deleteMeeting(Meeting meeting) {
        return meetings.remove(meeting.getId());
    }
}
