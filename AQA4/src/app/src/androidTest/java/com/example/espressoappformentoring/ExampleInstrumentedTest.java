package com.example.espressoappformentoring;

import android.content.pm.ActivityInfo;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.espressoappformentoring.TestViewAssertions.matchesWithMessage;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String ENTERED_TEXT = "Espresso keeps UI honest";

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @After
    public void restorePortraitOrientation() {
        activityRule.getScenario().onActivity(activity ->
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    @Test
    @TestDescription("Entered text is shown on the button and is cleared after rotation")
    public void enteredTextDisappearsAfterOrientationChange() {
        onView(withId(R.id.button_first)).perform(click());
        onView(withId(R.id.textForButton))
                .perform(typeText(ENTERED_TEXT), closeSoftKeyboard());
        onView(withId(R.id.textChange)).perform(click());

        onView(withId(R.id.textChange)).check(matchesWithMessage(
                "The button must display text copied from the input field",
                withText(ENTERED_TEXT)));

        activityRule.getScenario().onActivity(activity ->
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();

        onView(withId(R.id.textChange)).check(matchesWithMessage(
                "The dynamically entered text must disappear after orientation change",
                withText("Unedited")));
    }
}
