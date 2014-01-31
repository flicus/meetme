package org.ffff.meetme.model;

/**
 * Created by flicus on 27.01.14.
 */
public class MeetingDate {
    private String start;
    private String end;

    public MeetingDate() {
    }

    public MeetingDate(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "MeetingDate{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
