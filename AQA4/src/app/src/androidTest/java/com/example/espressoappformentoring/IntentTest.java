package com.example.espressoappformentoring;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.espressoappformentoring.TestViewAssertions.matchesWithMessage;
import static org.hamcrest.Matchers.allOf;

@SuppressWarnings("deprecation")
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public final IntentsTestRule<MainActivity> intentsRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    @TestDescription("MainActivity открывает SecondActivity с ожидаемым текстом")
    public void secondActivityStartsWithExpectedText() {
        onView(withId(R.id.textview_first)).check(matchesWithMessage(
                "Приложение должно запуститься на первом фрагменте MainActivity",
                allOf(isDisplayed(), withText(R.string.hello_first_fragment))));

        onView(withId(R.id.switchActivityButton)).perform(click());

        intended(hasComponent(SecondActivity.class.getName()));
        onView(withId(R.id.secondActText)).check(matchesWithMessage(
                "SecondActivity должна отображать ожидаемое приветствие",
                allOf(isDisplayed(), withText(R.string.hello_second_activity))));
    }
}
