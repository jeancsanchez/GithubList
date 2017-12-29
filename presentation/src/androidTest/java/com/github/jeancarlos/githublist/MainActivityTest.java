package com.github.jeancarlos.githublist;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.features.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * This class makes some instrumental tests for {@link MainActivity}
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */

@RunWith(JUnit4.class)
public class MainActivityTest {

    private static List<User> USERS = new ArrayList<>();

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        USERS.add(new User(
                1,
                "Jean Carlos",
                "jeancsanchez",
                "https://avatars2.githubusercontent.com/u/11152015?v=4",
                "",
                "",
                "",
                "",
                "",
                0,
                0,
                0,
                0
        ));
    }


    // Clicks on second item of list and show the details of it.
    @Test
    public void clicksOnFirstItemOfListAndShowDetails() {

        // Populate de RecyclerView with fake data
        mMainActivityTestRule.getActivity().runOnUiThread(() ->
                mMainActivityTestRule.getActivity().showUsers(USERS));


        // Check the loading
        onView(withId(R.id.swipeMainUsers))
                .check(matches(isDisplayed()));


        // Click on the first item
        onView(withId(R.id.recviewMainUsers))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        // Check if details screen is showing item details
        // FIXME: Needs IdlingResource.
        onView(withId(R.id.txtDetailNickname))
                .check(matches(isDisplayed()))
                .check(matches(withText(USERS.get(0).getLogin())));
    }
}
