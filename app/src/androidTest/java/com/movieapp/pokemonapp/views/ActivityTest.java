package com.movieapp.pokemonapp.views;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/***
 * This instrumentation class tests both
 * the main and Details Activity.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * This launched the app and scrolls the RecyclerView.
     */
    @Test
    public void appLaunchingAndListScrolling() {

        sleep();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(11));

        sleep();

    }

    /**
     * This tests the the launching of the MainActivity and as well as
     * launching the detailsActivity.
     */
    @Test
    public void launchDetailsActivity(){


        sleep();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(11));

        sleep();
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(11, ViewActions.click()));

        sleep();
        // After Navigating to Details Activity come back.
        Espresso.pressBack();

        sleep();
    }

    /**
     *
     * This method helps to test the onBefore Paging library method.
     */
    @Test
    public void testingOnBeforePagingMethod(){

        sleep();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(12));

        sleep();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(5));
        sleep();

    }


    /**
     * This is a sleeping method.
     */
    private void sleep() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
