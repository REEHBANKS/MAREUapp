package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Réunion A", "Peach", "#303030", "maxime@lamzone.com", Calendar.getInstance()),
            new Meeting(2, "Réunion B", "Mario", "#57d53b", "paul@lamzone.com",Calendar.getInstance()),
            new Meeting(3, "Réunion C", "Luigi", "#175732", "amandine@lamazone.com", Calendar.getInstance()),
            new Meeting(4, "Réunion D", "Toad", "#5b39c6", "franck@lamazone.com", Calendar.getInstance()),
            new Meeting(5, "Réunion E", "Yoshi", "#00c3ff", "daniel@lamzone.com", Calendar.getInstance())
            );

    static List<Meeting> generateNeighbours() {
        return new ArrayList<>(DUMMY_MEETING);
    }


}
