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
public class ManageFlashcardTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void manageFlashcard() {

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
        onView(withId(R.id.deckTitle)).perform(typeText("test deck"));
        onView(withId(R.id.Enter_Button)).perform(click());



        //add flashcard and select type
        onView(withId(R.id.addCard)).perform(click());
        onView(withId(R.id.createMenu)).perform(click());
        onView(withText("Standard")).perform(click());

        //create flashcard
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("This is a question"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("This is a question")).check(matches(withText(containsString("This is a question"))));



        //add flashcard and select type
        onView(withId(R.id.addCard)).perform(click());
        onView(withId(R.id.createMenu)).perform(click());
        onView(withText("Standard")).perform(click());

        //create flashcard
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("This is another question"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is another answer"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("This is another question")).check(matches(withText(containsString("This is another question"))));



        //add flashcard and select type
        onView(withId(R.id.addCard)).perform(click());
        onView(withId(R.id.createMenu)).perform(click());
        onView(withText("Standard")).perform(click());

        //create default flashcard
        onView(withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("This is the last question"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is the last answer"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("This is the last question")).check(matches(withText(containsString("This is the last question"))));



        //view flashcard
        onView(withId(R.id.viewCards)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());



        //edit flashcard (the last flashcard)
        onView(withId(R.id.modButton)).perform(click());

        onView(withId(R.id.title)).perform(click());
        onView(withText("This is the last question")).perform(replaceText("This is an edited question"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.body)).perform(click());
        onView(withText("This is the last answer")).perform(replaceText("This is an edited answer"));
        onView(withText("Save")).perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        //verify flashcard has been re added
        onView(withText("This is an edited question")).check(matches(withText(containsString("This is an edited question"))));



        //delete flashcard
        onView(withText("This is a question")).perform(click());
        onView(withId(R.id.delButton)).perform(click());

        //delete flashcard
        onView(withText("This is another question")).perform(click());
        onView(withId(R.id.delButton)).perform(click());

        //delete flashcard
        onView(withText("This is an edited question")).perform(click());
        onView(withId(R.id.delButton)).perform(click());



        //go back
        onView(withId(R.id.exitFlashList)).perform(click());

        //delete deck
        onView(withText("test deck")).perform(click());
        onView(withId(R.id.deleteDeck)).perform(click());


        //logout
        onView(withId(R.id.Back)).perform(click());

        //delete profile
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(withId(R.id.profiles)).perform(click());
        onView(withId(R.id.deleteProfile)).perform(click());
    }
}
