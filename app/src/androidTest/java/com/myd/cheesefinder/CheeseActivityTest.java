package com.myd.cheesefinder;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myd.cheesefinder.activities.CheeseActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by MYD on 9/17/17.
 *
 *
 */

@RunWith(AndroidJUnit4.class)
public class CheeseActivityTest {
    @Rule
    public ActivityTestRule<CheeseActivity> rule =
            new ActivityTestRule<>(CheeseActivity.class);


    @Test
    public void testSearch() throws Exception{
        CheeseActivity activity = rule.getActivity();
        View recycleView = activity.findViewById(R.id.list);
        Object adapter = ((RecyclerView)recycleView).getAdapter();
        assertThat(adapter, notNullValue());
        assertThat(adapter, instanceOf(RecyclerView.Adapter.class));
        RecyclerView.Adapter recycleViewAdapter = (RecyclerView.Adapter) adapter;

        ViewInteraction editTextViewInteraction = onView(withId(R.id.query_edit_text));
        editTextViewInteraction.perform(click()).check(matches(isDisplayed()));

        editTextViewInteraction.perform(typeText("bri"));
        SystemClock.sleep(CheeseActivity.DEBOUNCE_TIME_MS + 100);
        assertThat(recycleViewAdapter.getItemCount(), equalTo(3));
        editTextViewInteraction.perform(clearText());
        editTextViewInteraction.perform(typeText("1212312"));
        SystemClock.sleep(CheeseActivity.DEBOUNCE_TIME_MS + 100);
        assertThat(recycleViewAdapter.getItemCount(), equalTo(0));

        editTextViewInteraction.perform(clearText());
        ViewInteraction searchBtnViewInteraction = onView(withId(R.id.search_button));
        searchBtnViewInteraction.perform(click());
        SystemClock.sleep(CheeseActivity.DEBOUNCE_TIME_MS + 100);
        assertThat(recycleViewAdapter.getItemCount(), equalTo(380));
    }

    @Test
    public void testShowResult() {
        CheeseActivity activity = rule.getActivity();

        View recycleView = activity.findViewById(R.id.list);
        Object adapter = ((RecyclerView)recycleView).getAdapter();
        assertThat(adapter, notNullValue());
        assertThat(adapter, instanceOf(RecyclerView.Adapter.class));
        RecyclerView.Adapter recycleViewAdapter = (RecyclerView.Adapter) adapter;

        ViewInteraction editTextViewInteraction = onView(withId(R.id.query_edit_text));
        editTextViewInteraction.perform(typeText("1212312"));
        SystemClock.sleep(CheeseActivity.DEBOUNCE_TIME_MS + 100);

        onView(withText(R.string.nothing_found)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        ViewInteraction searchBtnViewInteraction = onView(withId(R.id.search_button));
        searchBtnViewInteraction.perform(click());

        onView(withText(R.string.nothing_found)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }


}
