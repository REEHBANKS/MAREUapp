package com.banks.mareu.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banks.mareu.databinding.ItemMeetingBinding;
import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingHolder> {

    private final List<Meeting> mMeeting;



    public MeetingRecyclerViewAdapter(List<Meeting> meeting)
    {
        mMeeting = meeting;
    }



    @NonNull
    @Override
    public MeetingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMeetingBinding mItemMeetingBinding = ItemMeetingBinding.inflate(layoutInflater, parent, false);
        return new MeetingHolder(mItemMeetingBinding);
    }

    @Override
    public void onBindViewHolder( MeetingRecyclerViewAdapter.MeetingHolder holder, int position) {
        Meeting meeting = mMeeting.get(position);
        holder.binding.itemMeetingName.setText(meeting.getMeetingName());
        holder.binding.itemTime.setText(meeting.getStringDate());
        holder.binding.itemRoomMeetingName.setText(meeting.getRoom().name());
        holder.binding.itemMeetingMail.setText(meeting.getMail());
        int color = Color.parseColor(meeting.getRoom().color);
        holder.binding.circle.setColorFilter(color);

    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    public static class MeetingHolder extends RecyclerView.ViewHolder {

        ItemMeetingBinding binding;

        public MeetingHolder( ItemMeetingBinding itemView){
           super(itemView.getRoot());
           this.binding= itemView;

        }
        }
    }

