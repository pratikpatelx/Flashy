package comp3350.flashy;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.flashy.presentation.Activities.MainActivity;

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
public class ManageFlashcardTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void manageFlashcard() {

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
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck"));
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
        Espresso.closeSoftKeyboard();
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is another answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 2")).check(matches(withText(containsString("Question 2"))));


        //add flashcard and select type
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Standard")).perform(click());

        //create default flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 3"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is the last answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("Question 3")).check(matches(withText(containsString("Question 3"))));


        //view flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.viewCards)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.nextButton)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.nextButton)).perform(click());


        //edit flashcard (the last flashcard)
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.modButton)).perform(click());

        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Question 3")).perform(replaceText("Edited question"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("This is the last answer")).perform(replaceText("This is an edited answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been re added
        onView(withText("Edited question")).check(matches(withText(containsString("Edited question"))));


        //delete flashcard
        Espresso.closeSoftKeyboard();
        onView(withText("Question 1")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());

        //delete flashcard
        Espresso.closeSoftKeyboard();
        onView(withText("Question 2")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());

        //delete flashcard
        Espresso.closeSoftKeyboard();
        onView(withText("Edited question")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());


        //go back
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());

        //delete deck
        Espresso.closeSoftKeyboard();
        onView(withText("test deck")).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteDeck)).perform(click());


        //logout
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Back)).perform(click());

        //delete profile
        Espresso.closeSoftKeyboard();
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deleteProfile)).perform(click());
    }
}
