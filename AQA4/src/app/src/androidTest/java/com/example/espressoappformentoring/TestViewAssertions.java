package com.example.espressoappformentoring;

import android.view.View;

import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

final class TestViewAssertions {

    private TestViewAssertions() {
    }

    static ViewAssertion matchesWithMessage(
            String failureMessage,
            Matcher<? super View> matcher
    ) {
        return (view, noViewFoundException) -> {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            assertThat(failureMessage, view, matcher);
        };
    }

    static ViewAssertion doesNotExistWithMessage(String failureMessage) {
        return (view, noViewFoundException) -> {
            if (view != null) {
                fail(failureMessage + "; unexpected view: " + view);
            }
        };
    }
}
