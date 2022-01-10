package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.banks.mareu.R;
import com.banks.mareu.databinding.ActivityAddMeetingBinding;
import com.banks.mareu.model.Room;

public class AddMeetingActivity extends AppCompatActivity {

    ActivityAddMeetingBinding binding;
    ArrayAdapter<Room> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAdapter = new ArrayAdapter<Room>(this, R.layout.room_item,Room.values());
        binding.autoComplete.setAdapter(mAdapter);

        binding.autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();

            }
        });





    }
}