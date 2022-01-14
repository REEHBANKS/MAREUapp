package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import di.DI;

import com.banks.mareu.RecyclerViewClickInterface;
import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.service.MeetingApiService;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity implements RecyclerViewClickInterface {

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

    public void initUi() {
        mMeetings = mMeetingApiService.getMeetings();
        mAdapter = new MeetingRecyclerViewAdapter(mMeetings, this);
        binding.listMeeting.setAdapter(mAdapter);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity2 = new Intent(getApplicationContext(),AddMeetingActivity.class);
                startActivity(otherActivity2);
            }
        });
    }

    @Override
    public void onDeleteClick(Meeting meeting, int position) {
        mMeetingApiService.deleteMeeting(meeting);
        mMeetings.remove(meeting);
        mAdapter.notifyItemRemoved(position);
    }
}