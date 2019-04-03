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
public class QuizModeTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void quizMode() {

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
        onView(ViewMatchers.withText("Multiple choice")).perform(click());

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
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        onView(withText("Standard")).perform(click());

        //create flashcard
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 2"));
        onView(withText("Save")).perform(click());
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 2")).check(matches(withText(containsString("Question 2"))));


        //add flashcard and select type
        onView(ViewMatchers.withId(R.id.addCard)).perform(click());
        onView(ViewMatchers.withId(R.id.createMenu)).perform(click());
        onView(withText("Fill in the blank")).perform(click());

        //create flashcard
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        onView(withText("Title")).perform(replaceText("Question 3"));
        onView(withText("Save")).perform(click());
        onView(ViewMatchers.withId(R.id.body)).perform(click());
        onView(withText("Example of front side of a flash card")).perform(replaceText("This is an answer"));
        Espresso.closeSoftKeyboard();
        onView(withText("Save")).perform(click());
        onView(ViewMatchers.withId(R.id.saveButton)).perform(click());

        //verify flashcard has been added
        onView(withText("Question 3")).check(matches(withText(containsString("Question 3"))));


        //go back
        onView(ViewMatchers.withId(R.id.exitFlashList)).perform(click());


        //start quiz
        onView(withText("test deck")).perform(click());
        onView(ViewMatchers.withId(R.id.startQuiz)).perform(click());

        //go through flashcards
        onView(ViewMatchers.withId(R.id.failButton)).perform(click());
        onView(ViewMatchers.withId(R.id.failButton)).perform(click());
        onView(ViewMatchers.withId(R.id.successButton)).perform(click());
        onView(ViewMatchers.withId(R.id.successButton)).perform(click());
        onView(ViewMatchers.withId(R.id.successButton)).perform(click());


        //logout
        onView(ViewMatchers.withId(R.id.Back)).perform(click());

        //delete profile
        onData(allOf(is(instanceOf(String.class)), is("user123"))).inAdapterView(ViewMatchers.withId(R.id.profiles)).perform(click());
        onView(ViewMatchers.withId(R.id.deleteProfile)).perform(click());
    }
}
