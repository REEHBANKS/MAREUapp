package com.banks.mareu.service;

import com.banks.mareu.R;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DummyMeetingGenerator {

    public static Calendar calendarFromDate(int year, int month, int day, int hour, int minute){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        return cal;
    }

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting( "Réunion A", Room.PEACH,  "maxime@lamzone.com, david@lamzone.com"
                    , calendarFromDate(2022, 3, 1,12,50)),
            new Meeting( "Réunion B", Room.MARIO,  "paul@lamzone.com, david@lamzone.com, didier@lamzone.com"
                    ,calendarFromDate(2022, 4, 1,13,0)),
            new Meeting( "Réunion C", Room.GENO, "amandine@lamazone.com,francois@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion D", Room.DAISY , "franck@lamazone.com, francois@lamzone.com"
                    , calendarFromDate(2022, 2, 2, 17, 30)),
            new Meeting( "Réunion E", Room.GLUIGI, "emmanuel@lamzone.com,emmanuel@lamzone.com, didier@lamzone.com"
                    , Calendar.getInstance()),
            new Meeting( "Réunion F", Room.TIARA, "francois@lamzone.com, leonard@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion G", Room.TOAD, "david@lamzone.com, francois@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion Z", Room.LUMA, "didier@lamzone.com,emmanuel@lamzone.com", Calendar.getInstance()),
            new Meeting( "Réunion H", Room.YOSHI, "didier@lamzone.com,emmanuel@lamzone.com"
                    , calendarFromDate(2022, 3, 1,12,50)),
            new Meeting( "Réunion I", Room.LUIGI, "samuel@lamzone.com,francois@lamzone.com ", Calendar.getInstance()),
            new Meeting( "Réunion J", Room.LUMA, "leonard@lamzone.com, francois@lamzone.com", Calendar.getInstance())
            );

    static List<Meeting> generateMeeting() {

        return DUMMY_MEETING;
    }


}
