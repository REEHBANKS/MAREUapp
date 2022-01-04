package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {


    // Get all my Meeting

    List<Meeting> getMeeting();

    // Delete a Meeting

    void deleteMeeting(Meeting meeting);

    // Create a Meeting

    void createMeeting(Meeting meeting);





}
