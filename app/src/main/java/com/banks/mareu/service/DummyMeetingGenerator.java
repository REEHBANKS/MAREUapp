package com.banks.mareu.service;

import com.banks.mareu.R;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting( "Réunion A", Room.PEACH,  "maxime@lamzone.com, david@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion B", Room.MARIO,  "paul@lamzone.com, david@lamzone.com, didier@lamzone.com",Calendar.getInstance()),
            new Meeting( "Réunion C", Room.GENO, "amandine@lamazone.com,francois@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion D", Room.DAISY , "franck@lamazone.com, francois@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion E", Room.GLUIGI, "emmanuel@lamzone.com,emmanuel@lamzone.com, didier@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion F", Room.TIARA, "francois@lamzone.com, leonard@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion G", Room.TOAD, "david@lamzone.com, francois@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion Z", Room.YOSHI, "didier@lamzone.com,emmanuel@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion H", Room.YOSHI, "didier@lamzone.com,emmanuel@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion I", Room.LUIGI, "samuel@lamzone.com,francois@lamzone.com ", Calendar.getInstance()),
            new Meeting( "Réunion J", Room.LUMA, "leonard@lamzone.com, francois@lamzone.com", Calendar.getInstance())
            );

    static List<Meeting> generateMeeting() {

        return DUMMY_MEETING;
    }


}
