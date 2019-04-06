package comp3350.flashy;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.flashy.presentation.Activity.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
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
    public void manageDeck() {

        //add user
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addProfile)).perform(click());
        onView(ViewMatchers.withId(R.id.Username)).perform(typeText("user123"));
        onView(ViewMatchers.withId(R.id.userPassword)).perform(typeText("123"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.regButton)).perform(click());

        //log in
        Espresso.closeSoftKeyboard();
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        onView(ViewMatchers.withId(R.id.profilePass)).perform(typeText("123"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Enter)).perform(click());

        //create deck
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createDeck)).perform(click());
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck 1"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Enter_Button)).perform(click());

        //add flashcard and select type
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Standard")).perform(click());

        //create flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 1"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 1")).check(matches(withText(containsString("Question 1"))));

        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withText("test deck 1")).check(matches(withText(containsString("test deck 1"))));


        //create deck
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createDeck)).perform(click());
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck 2"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Enter_Button)).perform(click());

        //add flashcard and select type
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Standard")).perform(click());

        //create flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 2"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 2")).check(matches(withText(containsString("Question 2"))));

        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withText("test deck 2")).check(matches(withText(containsString("test deck 2"))));


       //create deck
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createDeck)).perform(click());
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck 3"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Enter_Button)).perform(click());

        //add flashcard and select type
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Standard")).perform(click());


        //create flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 3"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 3")).check(matches(withText(containsString("Question 3"))));

        //go back
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withText("test deck 3")).check(matches(withText(containsString("test deck 3"))));


        //delete a deck
        Espresso.closeSoftKeyboard();
        onView(withText("test deck 1")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteDeck)).perform(click());

        //delete a deck
        Espresso.closeSoftKeyboard();
        onView(withText("test deck 2")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteDeck)).perform(click());

        //delete a deck
        Espresso.closeSoftKeyboard();
        onView(withText("test deck 3")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteDeck)).perform(click());

        //exit
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Back)).perform(click());

        //delete profile
        Espresso.closeSoftKeyboard();
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteProfile)).perform(click());
    }

}
