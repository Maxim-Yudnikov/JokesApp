package com.maxim.jokesapp

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.maxim.jokesapp.core.RecyclerViewMatcher
import com.maxim.jokesapp.core.lazyActivityScenarioRule
import com.maxim.jokesapp.data.cache.BaseRealmProvider
import com.maxim.jokesapp.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @Before
    fun before() {
        val realmProvider = BaseRealmProvider(ApplicationProvider.getApplicationContext(), true)

        realmProvider.provide().use {
            it.executeTransaction {
                it.deleteAll()
            }
        }
        activityTestRule.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                MainActivity::class.java
            )
        )
    }

    @Test
    fun test_get_joke_button() {
        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))
        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 1\nmock punchline 1")).check(matches(isDisplayed()))
    }

    @Test
    fun test_add_to_favorite_button() {
        onView(withText("No favorites! Add one by heart icon")).check(matches(isDisplayed()))
        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))

        onView(withId(R.id.favoriteButton)).perform(click())
        onView(withId(R.id.commonDataTextView)).check(matches(withText("mock text 0\nmock punchline 0")))
    }

    @Test
    fun test_add_to_favorite_twice() {
        onView(withText("No favorites! Add one by heart icon")).check(matches(isDisplayed()))
        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))

        onView(withId(R.id.favoriteButton)).perform(click())
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.commonDataTextView))
            .check(matches(withText("mock text 0\nmock punchline 0"))
        )

        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 1\nmock punchline 1")).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteButton)).perform(click())
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.commonDataTextView))
            .check(matches(withText("mock text 1\nmock punchline 1"))
        )
    }

    @Test
    fun test_add_to_favorite_and_remove() {
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.commonDataTextView))
            .check(matches(withText("No favorites! Add one by heart icon"))
            )
        onView(withText("Get joke")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))

        onView(withId(R.id.favoriteButton)).perform(click())
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.removeFromFavoriteButton))
            .perform(click())
        onView(withText("yes")).perform(click())
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.commonDataTextView))
            .check(matches(withText("No favorites! Add one by heart icon"))
            )
    }
}