package com.banks.mareu.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.banks.mareu.R;
import com.banks.mareu.databinding.DialogFragmentBinding;
import com.banks.mareu.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class MyDialogFragment extends AppCompatDialogFragment {

    DialogFragmentBinding mDialogFragmentBinding;
    SimpleDateFormat dateSdfDialog = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater li = LayoutInflater.from(getContext());
        mDialogFragmentBinding = DialogFragmentBinding.inflate(li);
        View v = mDialogFragmentBinding.getRoot();
        builder.setView(v);

        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(getContext(), R.layout.room_item, Room.values());
        mDialogFragmentBinding.autoCompleteFilter.setAdapter(adapter);
        mDialogFragmentBinding.autoCompleteFilter.setText(adapter.getItem(0).toString(), false);
        mDialogFragmentBinding.autoCompleteFilter.setThreshold(1);


        mDialogFragmentBinding.autoCompleteFilter.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();

        });

        mDialogFragmentBinding.dateEditTextFilter.setInputType(InputType.TYPE_NULL);
        mDialogFragmentBinding.dateEditTextFilter.setOnClickListener((View.OnClickListener) view -> {
            final Calendar cldr = Calendar.getInstance();
            int actualDay = cldr.get(Calendar.DAY_OF_MONTH);
            int actualMonth = cldr.get(Calendar.MONTH);
            int actualYear = cldr.get(Calendar.YEAR);
            DatePickerDialog picker;
            picker = new DatePickerDialog(getContext(),
                    (view1, yearSelected, monthSelected, dayOfSelected) -> {
                        Calendar cal = Calendar.getInstance();
                        cal.set(yearSelected, monthSelected, dayOfSelected);
                        mDialogFragmentBinding.dateEditTextFilter.setText(dateSdfDialog.format(cal.getTime()));
                    }, actualYear, actualMonth, actualDay);
            picker.show();
        });

        mDialogFragmentBinding.buttonActivedFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room roomSelected = Room.valueOf(mDialogFragmentBinding.autoCompleteFilter.getText().toString());
                String date = mDialogFragmentBinding.dateEditTextFilter.getText().toString();
                Calendar calendarMeetingFilter = Calendar.getInstance();
                try {
                    calendarMeetingFilter.setTime(Objects.requireNonNull(dateSdfDialog.parse(date )));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });




        mDialogFragmentBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogFragmentBinding.dateEditTextFilter.getText().clear();
                mDialogFragmentBinding.autoCompleteFilter.getText().clear();
            }
        });

        mDialogFragmentBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return builder.create();
    }


}
