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

import java.util.Calendar;
import java.util.List;

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
    public void setup(){
        service = DI.getMeetingApiService();

    }

    @Test
    public void getMeetingWithSuccess(){
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
    public void filteredMeetingWithSuccess(){
        service.filterMeetings(DummyMeetingGenerator.DUMMY_MEETING, Room.YOSHI, "");
    }





}