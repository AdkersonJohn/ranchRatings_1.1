@file:Suppress("DEPRECATION")

package com.example.ranchratings_11

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.ranchratings_11.dto.Restaurant
import com.example.ranchratings_11.service.RestaurantService
import org.junit.Rule
import org.junit.rules.TestRule
import com.example.ranchratings_11.ui.MainViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.*
import org.junit.Test
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class RestaurantUnitTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var mvm:MainViewModel

    private var restaurantService = mockk<RestaurantService>()

    private lateinit var workingRestaurant: Restaurant

    @Test
    fun searchForTexasRoadhouse_returnsTexasRoadhouse() {
        givenAFeedOfRestaurantDataAreAvailable()
        whenSearchForTexasRoadhouse()
        thenResultContainsTexasRoadhouse()
    }

    @Test
    fun searchForMitas_returnsMitas() {
        givenAFeedOfRestaurantDataAreAvailable()
        whenSearchForMitas()
        thenResultContainsMitas()
    }

    @Test
    fun searchForGibberish_returnsNothing() {
        givenAFeedOfRestaurantDataAreAvailable()
        whenSearchForGibberish()
        thenResultContainsNothing()
    }

    @Test
    fun postPositiveReview_containsPositiveReview() {
        givenTexasRoadhouse()
        whenPostPositive5StarReview()
        thenContainsPositive5StarReview()
    }

    @Test
    fun postNegativeReview_containsNegativeReview() {
        givenTexasRoadhouse()
        whenPostNegative1StarReview()
        thenContainsNegative1StarReview()
    }

    @Test
    fun postMultipleReviews_containsAllReviews() {
        givenTexasRoadhouse()
        whenPostPositive5StarReview()
        whenPostSlightlyPositive4StarReview()
        whenPostNegative1StarReview()
        thenContainsPositive5StarReview()
        thenContainsSlightlyPositive4StarReview()
        thenContainsNegative1StarReview()
    }

    @Test
    fun postMultipleReviews_calculatesAverageReview() {
        givenTexasRoadhouse()
        whenPostPositive5StarReview()
        whenPostSlightlyPositive4StarReview()
        whenPostNegative1StarReview()
        thenAverageRoundsTo3AndAHalfStars()
    }

    private fun givenAFeedOfRestaurantDataAreAvailable() {
        mvm = MainViewModel()
        createMockData()
    }

    private fun createMockData() {
        val allRestaurantsLiveData = MutableLiveData<ArrayList<Restaurant>>()
        val allRestaurants = ArrayList<Restaurant>()
        val texasRoadhouse = Restaurant("Texas Roadhouse")
        allRestaurants.add(texasRoadhouse)
        val mitas = Restaurant("Mitas")
        allRestaurants.add(mitas)
        allRestaurantsLiveData.postValue(allRestaurants)
        every {restaurantService.fetchRestaurants(or("Texas Roadhouse", "Mitas"))} returns allRestaurantsLiveData
        every {restaurantService.fetchRestaurants(not(or("Texas Roadhouse", "Mitas")))} returns MutableLiveData()
        mvm.restaurantService = restaurantService
    }

    private fun whenSearchForTexasRoadhouse() {
        mvm.fetchRestaurants("Texas Roadhouse")
    }

    private fun whenSearchForMitas() {
        mvm.fetchRestaurants("Mitas")
    }

    private fun whenSearchForGibberish() {
        mvm.fetchRestaurants("Gibberish")
    }

    private fun thenResultContainsTexasRoadhouse() {
        var texasRoadhouseFound = false
        mvm.restaurants.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach { restaurant ->
                if (restaurant.name == "Texas Roadhouse") {
                    texasRoadhouseFound = true
                }
            }
        }
        assertTrue(texasRoadhouseFound)
    }

    private fun thenResultContainsMitas() {
        var mitasFound = false
        mvm.restaurants.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach { restaurant ->
                if (restaurant.name == "Mitas") {
                    mitasFound = true
                }
            }
        }
        assertTrue(mitasFound)
    }

    private fun thenResultContainsNothing() {
        mvm.restaurants.observeForever {
            assertNotNull(it)
            assertTrue(it.size == 0)
        }
    }

    private fun givenTexasRoadhouse() {
        workingRestaurant = Restaurant("Texas Roadhouse")
    }

    private fun whenPostPositive5StarReview() {
        workingRestaurant.postReview(5.0, "Really Good", "Afoo De Critique", LocalDateTime.of(2020, 2, 20, 20, 20, 20))
    }

    private fun whenPostNegative1StarReview() {
        workingRestaurant.postReview(1.0, "Really Bad", "Waiter Tughcroud", LocalDateTime.of(2020, 2, 20, 20, 20, 20))
    }

    private fun whenPostSlightlyPositive4StarReview() {
        workingRestaurant.postReview(4.0, "It's fine", "Norman Mann", LocalDateTime.of(2020, 2, 20, 20, 20, 20))
    }

    private fun thenContainsPositive5StarReview() {
        var positiveReviewFound = false
        with(workingRestaurant.ratings) {
            assertNotNull(this)
            assertTrue(this.size > 0)
            this.forEach{
                if (it.rating == 5.0) {
                    positiveReviewFound = true
                }
            }
        }
        assertTrue(positiveReviewFound)
    }

    private fun thenContainsNegative1StarReview() {
        var negativeReviewFound = false
        with(workingRestaurant.ratings) {
            assertNotNull(this)
            assertTrue(this.size > 0)
            this.forEach{
                if (it.rating == 1.0) {
                    negativeReviewFound = true
                }
            }
        }
        assertTrue(negativeReviewFound)
    }

    private fun thenContainsSlightlyPositive4StarReview() {
        var slightlyPositiveReviewFound = false
        with(workingRestaurant.ratings) {
            assertNotNull(this)
            assertTrue(this.size > 0)
            this.forEach{
                if (it.rating == 4.0) {
                    slightlyPositiveReviewFound = true
                }
            }
        }
        assertTrue(slightlyPositiveReviewFound)
    }

    private fun thenAverageRoundsTo3AndAHalfStars() {
        assertTrue(workingRestaurant.ratings.size > 0)
        assertTrue(workingRestaurant.ratingAverageRoundedToHalf() == 3.5)
    }
}