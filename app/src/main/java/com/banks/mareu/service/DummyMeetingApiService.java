package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private final ArrayList<Meeting> meetings = new ArrayList<>( DummyMeetingGenerator.generateNeighbours());

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
