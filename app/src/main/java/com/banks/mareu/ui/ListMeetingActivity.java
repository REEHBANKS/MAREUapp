package com.banks.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import di.DI;

import com.banks.mareu.RecyclerViewClickInterface;
import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;
import com.banks.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingActivity extends AppCompatActivity implements RecyclerViewClickInterface, MyDialogFragment.FilterListenerInterface{

    public MeetingApiService mMeetingApiService;
    ActivityListMeetingBinding binding;
    MeetingRecyclerViewAdapter mAdapter;
    ArrayList<Meeting> listMeetings;


    @Override
    public void confirmFilter(Room selectedRoom, String selectedDate) {
        List<Meeting> filteredMeeting = mMeetingApiService.filterMeetings(listMeetings, selectedRoom, selectedDate);
        listMeetings.clear();
        listMeetings.addAll(filteredMeeting);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearFilter() {
        listMeetings.clear();
        listMeetings.addAll(mMeetingApiService.getMeetings());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.myToolbar);
        mMeetingApiService = DI.getMeetingApiService();
        initUi();

        binding.listMeeting.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    binding.addMeeting.hide();
                } else {
                    binding.addMeeting.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    public void initUi() {
        listMeetings = new ArrayList<>(mMeetingApiService.getMeetings());
        mAdapter = new MeetingRecyclerViewAdapter(listMeetings, this);
        binding.listMeeting.setAdapter(mAdapter);

        binding.addMeeting.setOnClickListener(view -> {
            Intent otherActivity2 = new Intent(getApplicationContext(), AddMeetingActivity.class);
            startActivity(otherActivity2);
        });

        binding.filterToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(), "Filtre");
            }
        });
    }

    @Override
    public void onDeleteClick(Meeting meeting, int position) {
        mMeetingApiService.deleteMeeting(meeting);
        listMeetings.remove(meeting);
        mAdapter.notifyItemRemoved(position);
    }

    public void onStart() {
        super.onStart();
        listMeetings.clear();
        listMeetings.addAll(mMeetingApiService.getMeetings());
        mAdapter.notifyDataSetChanged();
    }


}