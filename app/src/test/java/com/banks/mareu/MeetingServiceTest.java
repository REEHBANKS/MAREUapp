package com.banks.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;
import com.banks.mareu.service.DummyMeetingGenerator;
import com.banks.mareu.service.MeetingApiService;
import com.banks.mareu.ui.ListMeetingActivity;
import com.banks.mareu.ui.MyDialogFragment;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import di.DI;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getMeetingApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeeting = DummyMeetingGenerator.DUMMY_MEETING;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting neighbourToDelete = service.getMeetings().get(0);
        service.deleteMeeting(neighbourToDelete);
        assertFalse(service.getMeetings().contains(neighbourToDelete));
    }

    @Test
    public void createNewMeetingWithSuccess() {
        Meeting newMeeting = new Meeting("test", Room.LUMA, "rr@gmail.com"
                , Calendar.getInstance());
        service.createMeeting(newMeeting);
        assertTrue(service.getMeetings().contains(newMeeting));
    }

    @Test
    public void filteredMeetingWithSuccessS1() {
        List<Meeting> meetings = service.getMeetings();
        Room room = Room.YOSHI;
        String date = "";
        List<Meeting> result = service.filterMeetings(meetings, room, date);

        for(Meeting meeting : result){
            assertEquals(room, meeting.getRoom());
        }
    }

    @Test
    public void filteredMeetingWithSuccessS2()  {
        List<Meeting> meetings = service.getMeetings();
        String date = "01/04/2022";
        List<Meeting> result = service.filterMeetings(meetings, null, date);
        for(Meeting meeting : result) {
            SimpleDateFormat dateSdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            String dating = dateSdf.format(meeting.getDate().getTime());
            assertEquals(date, dating);
        }
    }

    @Test
    public void filteredMeetingWithSuccessS3()  {
        List<Meeting> meetings = service.getMeetings();
        Room room = Room.YOSHI;
        String date = "01/04/2022";

        List<Meeting> result = service.filterMeetings(meetings, room, date);

        for(Meeting meeting : result){
            SimpleDateFormat dateSdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            String dating = dateSdf.format(meeting.getDate().getTime());
            assertEquals(date, dating);
            assertEquals(room, meeting.getRoom());
        }
    }
}


