package comp3350.flashy;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.flashy.presentation.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ManageDeckTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
            public void createDeck() {
        //Register User
        onView(withId(R.id.addProfile)).perform(click());

        // add the user
        onView(withId(R.id.Username)).perform(typeText("test"));
        onView(withId(R.id.userPassword)).perform(typeText("test"));
        onView(withId(R.id.regButton)).perform(click());


        //log in
        onData(allOf(is(instanceOf(String.class)), is("test"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.profilePass)).perform(typeText("test"));
        onView(withId(R.id.Enter)).perform(click());

        //Create A new Deck
        onView(withId(R.id.createDeck)).perform(click());
        onView(withId(R.id.deckTitle)).perform(typeText("System Testing"));
        onView(withId(R.id.Enter_Button)).perform(click());

        onView(withId(R.id.exitFlashList)).perform(click());
        onView(withId(R.id.deck_list_item)).check(matches(withText(containsString("System Testing"))));
        onView(withId(R.id.Back)).perform(click());



    }

}
