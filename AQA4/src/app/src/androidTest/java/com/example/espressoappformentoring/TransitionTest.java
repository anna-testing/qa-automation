package com.example.espressoappformentoring;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.espressoappformentoring.TestViewAssertions.matchesWithMessage;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class TransitionTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    @TestDescription("All fragment and activity transitions work, including system Back")
    public void allScreenTransitionsWork() {
        assertFirstFragment("The test must start on the first fragment");

        onView(withId(R.id.button_first)).perform(click());
        assertSecondFragment("Next must open the second fragment");

        onView(withId(R.id.button_second)).perform(click());
        assertFirstFragment("Previous must return to the first fragment");

        onView(withId(R.id.button_first)).perform(click());
        assertSecondFragment("Next must open the second fragment again");

        pressBack();
        assertFirstFragment("System Back must return from the second fragment");

        onView(withId(R.id.switchActivityButton)).perform(click());
        onView(withId(R.id.secondActText)).check(matchesWithMessage(
                "Switch activity must open SecondActivity",
                allOf(isDisplayed(), withText(R.string.hello_second_activity))));

        pressBack();
        assertFirstFragment("System Back must return from SecondActivity to MainActivity");
    }

    private static void assertFirstFragment(String failureMessage) {
        onView(withId(R.id.textview_first)).check(matchesWithMessage(
                failureMessage,
                allOf(isDisplayed(), withText(R.string.hello_first_fragment))));
    }

    private static void assertSecondFragment(String failureMessage) {
        onView(withId(R.id.textForButton)).check(matchesWithMessage(
                failureMessage,
                isDisplayed()));
    }
}
