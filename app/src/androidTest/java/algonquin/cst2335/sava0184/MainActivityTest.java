package algonquin.cst2335.sava0184;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)


public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {


        ViewInteraction appCompatEditText = onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("123455"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("you shall not pass !")));
    }

    @Test
    public void testFindMissingUppercase() {


        ViewInteraction appCompatEditText = onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("password123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("you shall not pass !")));
    }

    @Test
    public void testFindMissinglowercase() {


        ViewInteraction appCompatEditText = onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("PASSWORD123#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("you shall not pass !")));
    }

    @Test
    public void testFindMissingnumber() {


        ViewInteraction appCompatEditText = onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("password#$*"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("you shall not pass !")));
    }

    @Test
    public void testFindMissingspecialcharacters() {


        ViewInteraction appCompatEditText = onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("password123"));

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("you shall not pass !")));
    }

}