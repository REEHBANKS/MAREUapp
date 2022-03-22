package com.banks.mareu;

import static android.text.method.Touch.scrollTo;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.banks.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.banks.mareu.model.Meeting;
import com.banks.mareu.model.Room;
import com.banks.mareu.service.MeetingApiService;
import com.banks.mareu.ui.AddMeetingActivity;
import com.banks.mareu.ui.ListMeetingActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import di.DI;

public class MeetingAddTest {

    private ListMeetingActivity mActivity;
    private MeetingApiService mApiService;
    private List<Meeting> mMeetings;
    private static final int ITEMS_COUNT = 11;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
    }

    @Test
    public void CheckIfWeViewTheAddPageIsDisplayWithNewMeeting(){
        String meetingTittle = "Dummy Meeting";
        String calendar = "03/03/2022";
        String meetingAddressMail = "test@test.com";

        // Given: Check that the Meeting list is displayed
        onView(allOf(withId(R.id.list_meeting), hasFocus()))
                .check(matches(hasMinimumChildCount(1)));
        // When: Click on button add
        onView(withId(R.id.add_meeting)).perform(click());
        // Then: We check if the title of the add page is displayed
        onView(withId(R.id.tittleAddPageText)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.VISIBLE)));

        // We write a tittle of new meeting
        onView(withId(R.id.nameMeeting)).perform(typeText(meetingTittle));
        Espresso.closeSoftKeyboard();

        // We choose the room : "yoshi"
        onView(withId(R.id.auto_complete)).perform(click());
        onData(Matchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .atPosition(1)
                .perform(click());
        onView(withId(R.id.auto_complete)).check(matches(withText("YOSHI")));

        // We choose the date
        onView(withId(R.id.date_edit_text)).perform(click());
        onView(withId(R.id.date_edit_text)).perform(click());
        onView(isAssignableFrom(DatePicker.class))
                .perform(setDate(1980, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.date_edit_text)).check(matches(withText("30/10/1980")));

        // We choose the time
        onView(withId(R.id.time_edit_text)).perform(click());
        onView(withId(R.id.time_edit_text)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10,30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_edit_text)).check(matches(withText("10:30")));

        // We write the address mail
        onView(withId(R.id.mailParticipant)).perform(typeText(meetingAddressMail));
        Espresso.closeSoftKeyboard();

        // We click on the button "AJOUTER"
        onView(withId(R.id.create)).perform(click());

        // We check if a new meeting is added
        onView(allOf(withId(R.id.list_meeting), hasFocus())).check(withItemCount(ITEMS_COUNT+1));
    }
}
