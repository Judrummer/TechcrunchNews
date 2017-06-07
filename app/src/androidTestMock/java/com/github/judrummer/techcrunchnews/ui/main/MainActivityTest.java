package com.github.judrummer.techcrunchnews.ui.main;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.github.judrummer.techcrunchnews.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction ivItemNewsImage0 = onView(
                allOf(withId(R.id.ivItemNewsImage),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(withId(R.id.rvNewsList),
                                                0),
                                        0),
                                0), isDisplayed()));
        ivItemNewsImage0.check(matches(isDisplayed()));

        ViewInteraction tvItemNewsTitle0 = onView(
                allOf(withId(R.id.tvItemNewsTitle),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                withId(R.id.rvNewsList),
                                                0),
                                        0),
                                1),
                        isDisplayed()));
        tvItemNewsTitle0.check(matches(withText("title1")));


        ViewInteraction tvItemNewsDescription0 = onView(
                allOf(withId(R.id.tvItemNewsDescription),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                withId(R.id.rvNewsList),
                                                0),
                                        0),
                                2),
                        isDisplayed()));
        tvItemNewsDescription0.check(matches(withText("description1 Developer & Drummer")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
