package com.example.sbohr002;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sbohr002.myapplication.R;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.ui.MoviesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MoviesActivityInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.sbohr002.myapplication", appContext.getPackageName());
    }


    @Rule
    public ActivityTestRule<MoviesActivity> mMoviesActivity =
            new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void check_if_MainActivity_returns_Correct_Header(){
        Espresso.onView(ViewMatchers.withId(R.id.movieNameText)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.lastupdatedTxt)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.movie)).check(matches(isDisplayed()));

    }


    @Test
    public void check_if_MainActivity_shows_RecyclerView(){
        Espresso.onView(ViewMatchers.withId(R.id.moviesList)).check(matches(isDisplayed()));

    }

    @Test
    public void check_if_MainActivity_RecyclerView_is_Clickable(){
        Espresso.onView(ViewMatchers.withId(R.id.moviesList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @Test
    public void check_if_MainActivity_RecyclerView_Count_Assertion(){
        Espresso.onView(ViewMatchers.withId(R.id.moviesList)).check(new RecyclerViewItemCountAssertion(10));

    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }


}
