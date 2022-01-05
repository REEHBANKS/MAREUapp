package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import di.DI;
import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.service.MeetingApiService;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {



    public MeetingApiService mMeetingApiService;
    ActivityListMeetingBinding binding;
    MeetingRecyclerViewAdapter mAdapter;
    List<Meeting> mMeetings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mMeetingApiService = DI.getMeetingApiService();
        initUi();

    }

    public void initUi(){
          mMeetings = mMeetingApiService.getMeetings();
          mAdapter = new MeetingRecyclerViewAdapter(mMeetings);
          binding.listMeeting.setAdapter(mAdapter);


    }

}