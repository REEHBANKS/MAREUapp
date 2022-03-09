package com.banks.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.banks.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.banks.mareu.ui.ListMeetingActivity;
import com.banks.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    // Size list meeting
    private static final int ITEMS_COUNT = 11;

    @Rule
        public ActivityTestRule<ListMeetingActivity> mActivityTestRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        ListMeetingActivity mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.list_meeting), hasFocus())).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
       onView(allOf(withId(R.id.list_meeting), hasFocus())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
       onView(allOf(withId(R.id.list_meeting), hasFocus())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 12
        onView(allOf(withId(R.id.list_meeting), hasFocus())).check(withItemCount(ITEMS_COUNT-1));
    }


}