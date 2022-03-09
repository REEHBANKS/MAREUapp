package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private final ArrayList<Meeting> meetings = new ArrayList<>( DummyMeetingGenerator.generateMeeting());



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


    @Override
    public List<Meeting> filterMeetings(List<Meeting> listMeetings, Room selectedRoom, String selectedDate) {
        ArrayList<Meeting> filteredMeeting = new ArrayList<>();

        for (Meeting meeting : listMeetings) {
            if (selectedDate.isEmpty() && selectedRoom != null) {
                if (meeting.getRoom() == selectedRoom) {
                    filteredMeeting.add(meeting);
                }
            }
            if (!selectedDate.isEmpty() && selectedRoom == null) {
                if (meeting.getStringOnlyDate().equals(selectedDate)) {
                    filteredMeeting.add(meeting);
                }
            }  if (!selectedDate.isEmpty() && selectedRoom != null) {
                if (meeting.getRoom() == selectedRoom && meeting.getStringOnlyDate().equals(selectedDate)) {
                    filteredMeeting.add(meeting);
                }
            }

        }
        return filteredMeeting;
    }


}
