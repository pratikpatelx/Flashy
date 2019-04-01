package comp3350.flashy;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
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
        onView(ViewMatchers.withId(R.id.addProfile)).perform(click());
        onView(ViewMatchers.withId(R.id.Username)).perform(typeText("user123"));
        onView(ViewMatchers.withId(R.id.userPassword)).perform(typeText("123"));
        onView(ViewMatchers.withId(R.id.regButton)).perform(click());

        //log in
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        onView(ViewMatchers.withId(R.id.profilePass)).perform(typeText("123"));
        onView(ViewMatchers.withId(R.id.Enter)).perform(click());

        //create deck
        onView(ViewMatchers.withId(R.id.createDeck)).perform(click());
        onView(ViewMatchers.withId(R.id.deckTitle)).perform(typeText("test deck"));
        onView(ViewMatchers.withId(R.id.Enter_Button)).perform(click());



        //add flashcard and select type
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        onView(withText("Multiple choice")).perform(click());

        //create flashcard

        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("This is a question"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("This is a question")).check(matches(withText(containsString("This is a question"))));



        //add flashcard and select type
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        onView(withText("Multiple choice")).perform(click());

        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("This is another question"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("This is another question")).check(matches(withText(containsString("This is another question"))));



        //add flashcard and select type
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        onView(withText("Multiple choice")).perform(click());

        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(typeText("Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(typeText("Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(typeText("Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(typeText("Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(typeText("This is the last question"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flash card has been added to the flash card list
        onView(withText("This is the last question")).check(matches(withText(containsString("This is the last question"))));



        //view flashcard
        onView(ViewMatchers.withId(R.id.viewCards)).perform(click());
        onView(ViewMatchers.withId(R.id.nextButton)).perform(click());
        onView(ViewMatchers.withId(R.id.nextButton)).perform(click());


        //edit flashcard (the last flashcard)
        onView(ViewMatchers.withId(R.id.modButton)).perform(click());

        onView(ViewMatchers.withId(R.id.wrongMC3)).perform(replaceText("1 Wrongest answer"));
        onView(ViewMatchers.withId(R.id.wrongMC2)).perform(replaceText("2 Wronger answer"));
        onView(ViewMatchers.withId(R.id.wrongMC1)).perform(replaceText("3 Wrong answer"));
        onView(ViewMatchers.withId(R.id.correctMC)).perform(replaceText("4 Correct answer"));
        onView(ViewMatchers.withId(R.id.mcQuestion)).perform(replaceText("This is an edited question"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been re added
        onView(withText("This is an edited question")).check(matches(withText(containsString("This is an edited question"))));



        //delete flashcard
        onView(withText("This is a question")).perform(click());
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());

        //delete flashcard
        onView(withText("This is another question")).perform(click());
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());

        //delete flashcard
        onView(withText("This is an edited question")).perform(click());
        onView(ViewMatchers.withId(R.id.delButton)).perform(click());



        //go back
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());

        //delete deck
        onView(withText("test deck")).perform(click());
        onView(ViewMatchers.withId(R.id.deleteDeck)).perform(click());


        //logout
        onView(ViewMatchers.withId(R.id.Back)).perform(click());

        //delete profile
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        onView(ViewMatchers.withId(R.id.deleteProfile)).perform(click());
    }
}
