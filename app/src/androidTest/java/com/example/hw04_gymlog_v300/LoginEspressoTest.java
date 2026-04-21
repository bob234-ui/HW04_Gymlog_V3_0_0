package com.example.hw04_gymlog_v300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginEspressoTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> rule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void loginAddLogLogoutLoginAgainSeeLog() {
        onView(withId(R.id.usernameEditText)).perform(replaceText("admin1"));
        onView(withId(R.id.passwordEditText)).perform(replaceText("admin1"));
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.logEditText)).perform(replaceText("Bench Press"));
        onView(withId(R.id.addButton)).perform(click());

        onView(withText("Bench Press")).check(matches(isDisplayed()));
    }
}