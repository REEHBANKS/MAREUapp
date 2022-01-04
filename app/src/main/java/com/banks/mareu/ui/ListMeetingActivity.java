package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.banks.mareu.R;
import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.service.MeetingApiService;

import java.util.ArrayList;

public class ListMeetingActivity extends AppCompatActivity {

    public MeetingApiService mMeetingApiService;
    ActivityListMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.listMeeting.setLayoutManager(new LinearLayoutManager(this));
        mMeetingApiService.getMeeting();

    }


}