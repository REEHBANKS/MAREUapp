package com.banks.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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
import java.util.Objects;

import di.DI;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    public MeetingApiService mMeetingApiService;
    public Meeting mMeeting;

    SimpleDateFormat dateSdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
    SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm", Locale.FRANCE);
    SimpleDateFormat dateTimeSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mMeetingApiService = DI.getMeetingApiService();

        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(this, R.layout.room_item, Room.values());
        binding.autoComplete.setAdapter(adapter);

        binding.autoComplete.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();

        });

        binding.dateEditText.setInputType(InputType.TYPE_NULL);
        binding.dateEditText.setOnClickListener((View.OnClickListener) view -> {
            final Calendar cldr = Calendar.getInstance();
            int actualDay = cldr.get(Calendar.DAY_OF_MONTH);
            int actualMonth = cldr.get(Calendar.MONTH);
            int actualYear = cldr.get(Calendar.YEAR);
            // date picker dialog
            DatePickerDialog picker;
            picker = new DatePickerDialog(AddMeetingActivity.this,
                    (view1, yearSelected, monthSelected, dayOfSelected) -> {
                        Calendar cal = Calendar.getInstance();
                        cal.set(yearSelected, monthSelected, dayOfSelected);
                        binding.dateEditText.setText(dateSdf.format(cal.getTime()));
                    }, actualYear, actualMonth, actualDay);
            picker.show();
        });

        binding.timeEditText.setInputType(InputType.TYPE_NULL);
        binding.timeEditText.setOnClickListener(view -> {
            final Calendar cldr = Calendar.getInstance();
            int actualHour = cldr.get(Calendar.HOUR_OF_DAY);
            int actualMinutes = cldr.get(Calendar.MINUTE);
            // time picker dialog
            TimePickerDialog picker;
            picker = new TimePickerDialog(AddMeetingActivity.this,
                    (tp, hourSelected, minuteSelected) -> {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourSelected);
                        calendar.set(Calendar.MINUTE, minuteSelected);
                        binding.timeEditText.setText(timeSdf.format(calendar.getTime()));
                    }, actualHour, actualMinutes, true);
            picker.show();
        });


        binding.create.setOnClickListener(view -> {
            //Get Name
            String nameMeeting = Objects.requireNonNull(binding.nameMeeting.getText()).toString();
            if (nameMeeting.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_name_meeting), Toast.LENGTH_SHORT).show();
                return;
            }
            //Get Room
            String roomString = binding.autoComplete.getText().toString();
            if (roomString.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_room), Toast.LENGTH_SHORT).show();
                return;
            }
            Room roomSelected = Room.valueOf(binding.autoComplete.getText().toString());
            Log.d("Renelle", "room" + roomSelected);

            // Get date & time
            String date = binding.dateEditText.getText().toString();
            if (date.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_date), Toast.LENGTH_SHORT).show();
                return;
            }

            String time = binding.timeEditText.getText().toString();
            if (time.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_time), Toast.LENGTH_SHORT).show();
                return;
            }
            Calendar calendarMeeting = Calendar.getInstance();
            try {
                calendarMeeting.setTime(Objects.requireNonNull(dateTimeSdf.parse(date + " " + time)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Get Participants
            String part = Objects.requireNonNull(binding.mailParticipant.getText()).toString();
            if (part.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_address), Toast.LENGTH_SHORT).show();
                return;
            }

            Meeting meeting = new Meeting(
                    nameMeeting,
                    roomSelected,
                    part,
                    calendarMeeting);
            mMeetingApiService.createMeeting(meeting);
            finish();

        });
    }
}


