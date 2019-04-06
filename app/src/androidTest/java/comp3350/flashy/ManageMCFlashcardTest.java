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
public class ManageMCFlashcardTest {

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
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.Enter_Button)).perform(click());


        //add flashcard and select type
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Multiple choice")).perform(click());

        //create flashcard
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("Question 1"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 1")).check(matches(withText(containsString("Question 1"))));


        //add flashcard and select type
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withText("Multiple choice")).perform(click());

        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("Question 2"));
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
        onView(withText("Multiple choice")).perform(click());

        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("Question 3"));
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

        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(replaceText("Wrongest answer!"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(replaceText("Wronger answer!"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(replaceText("Wrong answer!"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(replaceText("Correct answer!"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(replaceText("Edited question"));
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
