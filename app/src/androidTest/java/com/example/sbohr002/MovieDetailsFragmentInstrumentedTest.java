package com.example.sbohr002;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sbohr002.myapplication.R;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.ui.MoviesActivity;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.ui.MovieDetailsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(AndroidJUnit4.class)
public class MovieDetailsFragmentInstrumentedTest {

    @Rule
    public ActivityTestRule<MoviesActivity> activityActivityTestRule = new ActivityTestRule<>(MoviesActivity.class);

    @Before
    public void init(){


    }

    @Test
    public void fragment_can_be_instantiated() {
        activityActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance();
                FragmentTransaction beginTransaction =  activityActivityTestRule.getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                beginTransaction.addToBackStack(movieDetailsFragment.getClass().getName());
                beginTransaction.replace(R.id.container, movieDetailsFragment, movieDetailsFragment.getClass().getName());
                beginTransaction.commit();
            }
        });
        // Then use Espresso to test the Fragment
//        check_if_MainActivity_RecyclerView_is_Clickable();
        check_if_MovieDetailsFragment_returns_Correct_Header();
        check_if_MovieDetailsFragment_returns_data();
        check_if_MovieDetailsFragment__RecyclerView_Count_Assertion();
    }

    @Before
    public void check_if_MainActivity_RecyclerView_is_Clickable(){
        Espresso.onView(ViewMatchers.withId(R.id.moviesList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }
    @Test
    public void check_if_MovieDetailsFragment_returns_Correct_Header(){
        Espresso.onView(ViewMatchers.withId(R.id.back_button)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_title)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.movieNameTxt)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.descTxt)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.scoreLabel)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.actorsTxt)).check(matches(isDisplayed()));

    }

    @Test
    public void check_if_MovieDetailsFragment_returns_data(){
        Espresso.onView(ViewMatchers.withId(R.id.movieNameTxt)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.description)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.score)).check(matches(isDisplayed()));
    }

    @Test
    public void check_if_MovieDetailsFragment__RecyclerView_Count_Assertion(){
        Espresso.onView(ViewMatchers.withId(R.id.actorsList)).check(new MovieDetailsFragmentInstrumentedTest.RecyclerViewItemCountAssertion(3));

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
