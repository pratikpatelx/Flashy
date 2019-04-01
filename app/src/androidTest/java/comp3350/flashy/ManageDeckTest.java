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
        //add user
        onView(withId(R.id.addProfile)).perform(click());
        onView(withId(R.id.Username)).perform(typeText("user123"));
        onView(withId(R.id.userPassword)).perform(typeText("123"));
        onView(withId(R.id.regButton)).perform(click());

        //log in
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.profilePass)).perform(typeText("123"));
        onView(withId(R.id.Enter)).perform(click());

        //create deck
        onView(withId(R.id.createDeck)).perform(click());
        onView(withId(R.id.deckTitle)).perform(typeText("test deck 1"));
        onView(withId(R.id.Enter_Button)).perform(click());
        onView(withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withId(R.id.deck_list_item)).check(matches(withText(containsString("test deck 1"))));


        //create deck
        onView(withId(R.id.createDeck)).perform(click());
        onView(withId(R.id.deckTitle)).perform(typeText("test deck 2"));
        onView(withId(R.id.Enter_Button)).perform(click());
        onView(withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withText("test deck 2")).check(matches(withText(containsString("test deck 2"))));

        //create deck
        onView(withId(R.id.createDeck)).perform(click());
        onView(withId(R.id.deckTitle)).perform(typeText("test deck 3"));
        onView(withId(R.id.Enter_Button)).perform(click());

        //create default flashcard
        onView(withId(R.id.addCard)).perform(click());
        //question
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("This is a question"));
        onView(withText("Save")).perform(click());
        //answer
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withId(R.id.flashListItem)).check(matches(withText(containsString("This is a question"))));

        //delete flashcard
        onView(withText("This is a question")).perform(click());
        onView(withId(R.id.delButton)).perform(click());

        //go back
        onView(withId(R.id.exitFlashList)).perform(click());

        //verify if deck has been added to deck list
        onView(withText("test deck 3")).check(matches(withText(containsString("test deck 3"))));

        //delete a deck
        onView(withText("test deck 1")).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());

        //delete a deck
        onView(withText("test deck 2")).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());

        //delete a deck
        onView(withText("test deck 3")).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());

        //exit
        onView(withId(R.id.Back)).perform(click());
    }

}
