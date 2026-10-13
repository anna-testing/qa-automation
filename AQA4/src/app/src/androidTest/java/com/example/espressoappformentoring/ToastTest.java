package com.example.espressoappformentoring;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.espressoappformentoring.TestViewAssertions.matchesWithMessage;

@RunWith(AndroidJUnit4.class)
public class ToastTest {

    private static final String EXPECTED_MESSAGE = "May be some teast text";

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    @TestDescription("The mail floating action button shows the expected message")
    public void mailButtonShowsExpectedMessage() {
        onView(withId(R.id.fab)).perform(click());

        onView(withText(EXPECTED_MESSAGE)).check(matchesWithMessage(
                "Clicking the mail button must show the expected Snackbar text",
                isDisplayed()));
    }
}
