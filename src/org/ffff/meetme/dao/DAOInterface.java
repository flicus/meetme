package org.ffff.meetme.dao;

import org.ffff.meetme.model.Contact;
import org.ffff.meetme.model.Meeting;

import java.util.Collection;

/**
 * Created by flicus on 27.01.14.
 */
public interface DAOInterface {

    public Collection<Meeting> getMeetings(String email);
    public Collection<Meeting> getMeetings(Contact member);

    public Meeting addMeeting(Meeting meeting);
    public Meeting deleteMeeting(Meeting meeting);

}
