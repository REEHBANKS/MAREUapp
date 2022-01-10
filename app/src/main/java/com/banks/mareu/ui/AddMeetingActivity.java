package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.banks.mareu.R;
import com.banks.mareu.model.Room;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        Room.values();
    }
}