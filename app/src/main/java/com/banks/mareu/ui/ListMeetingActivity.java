package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.service.DummyMeetingApiService;
import com.banks.mareu.service.MeetingApiService;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {



    public MeetingApiService mMeetingApiService = new DummyMeetingApiService();
    ActivityListMeetingBinding binding;
    MeetingRecyclerViewAdapter mAdapter;
    List<Meeting> mMeetings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();

    }

    public void initUi(){
          mMeetings = mMeetingApiService.getMeetings();
          mAdapter = new MeetingRecyclerViewAdapter(mMeetings);
          binding.listMeeting.setAdapter(mAdapter);


    }

}