package com.example.espressoappformentoring;

import android.content.Context;
import android.view.InputDevice;
import android.view.MotionEvent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.espressoappformentoring.TestViewAssertions.doesNotExistWithMessage;
import static com.example.espressoappformentoring.TestViewAssertions.matchesWithMessage;

@RunWith(AndroidJUnit4.class)
public class ToolbarTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    @TestDescription("Выпадающее меню показывает Settings и закрывается при нажатии вне меню")
    public void settingsMenuOpensAndClosesOnOutsideClick() {
        Context context = ApplicationProvider.getApplicationContext();
        openActionBarOverflowOrOptionsMenu(context);

        onView(withText(R.string.action_settings)).check(matchesWithMessage(
                "Выпадающее меню должно содержать видимый пункт Settings",
                isDisplayed()));

        onView(isRoot()).perform(new GeneralClickAction(
                Tap.SINGLE,
                view -> new float[]{
                        view.getResources().getDisplayMetrics().widthPixels / 2.0f,
                        view.getResources().getDisplayMetrics().heightPixels / 2.0f
                },
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY));

        onView(withText(R.string.action_settings)).check(doesNotExistWithMessage(
                "Меню Settings должно исчезнуть после нажатия вне него"));
    }
}
