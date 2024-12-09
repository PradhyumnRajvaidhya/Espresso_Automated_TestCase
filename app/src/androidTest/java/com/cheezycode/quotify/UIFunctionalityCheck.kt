package com.cheezycode.quotify

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.hamcrest.CoreMatchers.allOf

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UIFunctionalityCheck {

    val HEADER: String = "QUOTIFY"
    val NEXT_BUTTON: String = "NEXT"
    val PREVIOUS_BUTTON: String = "PREVIOUS"
    val QUOTE_NEXT3: String = "Difficulties increase the nearer we get to the goal."
    val QUOTE_NEXT5: String = "Be the chief but never the lord."
    val QUOTE_PREVIOUS3: String = "Using the power of decision gives you the capacity to get past any excuse to change any and every part of your life in an instant."
    val QUOTE_PREVIOUS5: String = "Here is one quality that one must possess to win, and that is definiteness of purpose, the knowledge of what one wants, and a burning desire to possess it."

    @Test
    fun testALaunchAppOnAVD(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testBDisplayHomePageElements() {
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        onView(withText(HEADER)).check(matches(isDisplayed()))
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()))
        onView(withText(NEXT_BUTTON)).check(matches(isDisplayed()))
        onView(withText(PREVIOUS_BUTTON)).check(matches(isDisplayed()))
    }

    @Test
    fun testCClickNextButton3Times(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        for (times in 1..3){
            onView(withText(NEXT_BUTTON)).perform(click())
        }
        onView(withId(R.id.quoteText)).check(matches(withText(QUOTE_NEXT3)));
    }

    @Test
    fun testDClickNextButton5Times(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        for (times in 1..5){
            onView(withText(NEXT_BUTTON)).perform(click())
        }
        onView(withId(R.id.quoteText)).check(matches(withText(QUOTE_NEXT5)))
    }

    @Test
    fun testEClickPreviousButton3Times(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        for (times in 1..3){
            onView(withText(PREVIOUS_BUTTON)).perform(click())
        }
        onView(withId(R.id.quoteText)).check(matches(withText(QUOTE_PREVIOUS3)))
    }

    @Test
    fun testFClickPreviousButton5Times(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        for (times in 1..5){
            onView(withText(PREVIOUS_BUTTON)).perform(click())
        }
        onView(withId(R.id.quoteText)).check(matches(withText(QUOTE_PREVIOUS5)))
    }

    @Test
    fun testGDriveShareButtonFunctionality(){
        val mainActivity = ActivityScenario.launch(MainActivity::class.java)
        Intents.init()
        onView(withId(R.id.floatingActionButton)).perform(click())
        Intents.intended(allOf(
            hasAction(Intent.ACTION_SEND),
            hasExtra(Intent.EXTRA_TEXT, "Genius is one percent inspiration and ninety-nine percent perspiration.")
        ))
        Intents.release()
    }

}
