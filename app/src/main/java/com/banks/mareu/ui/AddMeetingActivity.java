package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.banks.mareu.R;
import com.banks.mareu.databinding.ActivityAddMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;
import com.banks.mareu.service.MeetingApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    public MeetingApiService mMeetingApiService;
    public Meeting mMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(this, R.layout.room_item, Room.values());
        binding.autoComplete.setAdapter(adapter);

        binding.autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String item = adapterView.getItemAtPosition(i).toString();


            }
        });

        binding.editText1.setInputType(InputType.TYPE_NULL);

        binding.editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker;
                picker = new DatePickerDialog(AddMeetingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                binding.editText1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        binding.editText2.setInputType(InputType.TYPE_NULL);
        binding.editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                TimePickerDialog picker;
                picker = new TimePickerDialog(AddMeetingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                binding.editText2.setText(sHour + ":" + sMinute);

                                String call = binding.editText2.getText().toString();
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });



        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.FRANCE);
                try {
                    cal.setTime(sdf.parse(binding.editText2.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Meeting meeting = new Meeting(System.currentTimeMillis(),
                binding.nameMeeting.getEditText().getText().toString(),
                Room.valueOf(binding.autoComplete.getText().toString()),
                binding.mailParticipant.getEditText().getText().toString(),
                cal);

                 mMeetingApiService.createMeeting(meeting);
                 finish();



            }
        });

        }


}


