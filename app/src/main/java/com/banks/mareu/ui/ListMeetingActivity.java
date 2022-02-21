package com.banks.mareu.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import di.DI;

import com.banks.mareu.R;
import com.banks.mareu.RecyclerViewClickInterface;
import com.banks.mareu.databinding.ActivityListMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;
import com.banks.mareu.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ListMeetingActivity extends AppCompatActivity implements RecyclerViewClickInterface, MyDialogFragment.DataListener {

    public MeetingApiService mMeetingApiService;
    ActivityListMeetingBinding binding;
    MeetingRecyclerViewAdapter mAdapter;
    ArrayList<Meeting> listMeetings;



    @Override
    public void confirmFilter(Room selectedRoom, String selectedDate) {
        List<Meeting> filteredMeeting = filterMeetings(listMeetings, selectedRoom, selectedDate);
        listMeetings.clear();
        listMeetings.addAll(filteredMeeting);
        mAdapter.notifyDataSetChanged();
    }

    private List<Meeting> filterMeetings(List<Meeting> listMeetings, Room selectedRoom, String selectedDate) {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.myToolbar);
        mMeetingApiService = DI.getMeetingApiService();
        initUi();




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