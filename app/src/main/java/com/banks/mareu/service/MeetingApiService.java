package com.banks.mareu.service;

import com.banks.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {



    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);







}
