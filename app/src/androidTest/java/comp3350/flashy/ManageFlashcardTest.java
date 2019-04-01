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
public class ManageFlashcardTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createFlashCard() {

        //add user
        onView(withId(R.id.addProfile)).perform(click());
        onView(withId(R.id.Username)).perform(typeText("test"));
        onView(withId(R.id.userPassword)).perform(typeText("test"));
        onView(withId(R.id.regButton)).perform(click());

        //log in
        onData(allOf(is(instanceOf(String.class)), is("test"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.profilePass)).perform(typeText("test"));
        onView(withId(R.id.Enter)).perform(click());

        //create deck
        onView(withId(R.id.createDeck)).perform(click());
        onView(withId(R.id.deckTitle)).perform(typeText("System Testing"));
        onView(withId(R.id.Enter_Button)).perform(click());


        //create default flashcard
        onView(withId(R.id.addCard)).perform(click());
        //question
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("System Test"));
        onView(withText("Save")).perform(click());
        //answer
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is a system Test"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withId(R.id.flashListItem)).check(matches(withText(containsString("System Test"))));



        //create default flashcard
        onView(withId(R.id.addCard)).perform(click());
        //question
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("System Test 2"));
        onView(withText("Save")).perform(click());
        //answer
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is a system Test 2"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("System Test 2")).check(matches(withText(containsString("System Test 2"))));



        //create default flashcard
        onView(withId(R.id.addCard)).perform(click());
        //question
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("System Test 3"));
        onView(withText("Save")).perform(click());
        //answer
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is a system Test 3"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("System Test 3")).check(matches(withText(containsString("System Test 3"))));



        //view flashcard
        onView(withText("System Test")).perform(click());

        //flip through flashcards
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());



        //edit flashcard

        onView(withId(R.id.modButton)).perform(click());

        //question
        onView(withId(R.id.title)).perform(click());
        onView(withText("System Test 3")).perform(replaceText("System Test 4"));
        onView(withText("Save")).perform(click());
        //answer
        onView(withId(R.id.body)).perform(click());
        onView(withText("This is a system Test 3")).perform(replaceText("This is a system Test 4"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("System Test 4")).check(matches(withText(containsString("System Test 4"))));



        //delete flashcard
        onView(withText("System Test 2")).perform(click());
        onView(withId(R.id.delButton)).perform(click());



        //exit
       onView(withId(R.id.exitFlashList)).perform(click());
       // onView(withId(R.id.Back)).perform(click());

    }
}
/*
        //delete flashcard

         /edit flashcard
        onView(withText("System Test")).perform(click());
        onView(withId(R.id.modButton)).perform(click());


        //verify deck has the card added to the deck
        onView(withId(R.id.exitFlashList)).perform(click());
        //onView(withId(R.id.deckList)).perform(click());


        onView(withId(R.id.deck_list_item)).check(matches(withText(containsString("System Testing"))));

        //onView(withId(R.id.deck_img)).perform(click());

        //log out to main page
        onView(withId(R.id.Back)).perform(click());
        //onView(withId(R.id.logOut)).perform(click());

        //log in
        onData(allOf(is(instanceOf(String.class)), is("test"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.profilePass)).perform(typeText("test"));
        onView(withId(R.id.Enter)).perform(click());

        //select deck and delete
        onView(withId(R.id.deck_list_item)).check(matches(withText(containsString("System Testing")))).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());
    }*/
/*
    @Test
    public void deleteFlashCard() {
        //log in
        onData(allOf(is(instanceOf(String.class)), is("test"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.profilePass)).perform(typeText("test"));
        onView(withId(R.id.Enter)).perform(click());

        //select deck and delete
        onView(withId(R.id.deck_list_item)).check(matches(withText(containsString("System Testing")))).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());
/*
    */
