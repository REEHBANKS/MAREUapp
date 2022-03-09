package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();
     List<Meeting> filterMeetings(List<Meeting> listMeetings, Room selectedRoom, String selectedDate);
    void deleteMeeting(Meeting meeting);
    void createMeeting(Meeting meeting);
}
