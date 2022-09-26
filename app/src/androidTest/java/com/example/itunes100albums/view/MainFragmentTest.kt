package com.example.itunes100albums.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.itunes100albums.MainActivity
import com.example.itunes100albums.R
import com.example.itunes100albums.adapter.FavoritesFragmentRecyclerViewAdapter
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import com.example.itunes100albums.room.Entity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.not
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    @Test
    fun test(){
        //val activityScenario =
        ActivityScenario.launch(MainActivity::class.java)

        //check if the main fragment is in view
        onView(withId(R.id.rootLayoutFragmentMain)).check(matches(isDisplayed()))

        onView((withId(R.id.fab))).perform(click())

        //check if the favorites fragment gets in view when the fab is clicked
        onView(withId(R.id.favoritesFragmentRootLayout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.recyclerViewFavoriteAlbums)).check(matches(isDisplayed()))

    }

    //Further UI tests can be written to test the data in recyclerview like the following
/*    @Test//(expected = PerformException::class)
    fun itemWithPositionExists() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //check if the main fragment is in view
        onView(withId(R.id.rootLayoutFragmentMain)).check(matches(isDisplayed()))

        onView((withId(R.id.fab))).perform(click())

        //check if the favorites fragment gets in view when the fab is clicked
        onView(withId(R.id.favoritesFragmentRootLayout)).check(matches(isDisplayed()))

        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.recyclerViewFavoriteAlbums))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.actionOnItemAtPosition<MainRecyclerViewAdapter.ArtViewHolder>(1, click())
            )
    }*/

}