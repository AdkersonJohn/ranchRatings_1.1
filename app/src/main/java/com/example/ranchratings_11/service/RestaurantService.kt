package com.example.ranchratings_11.service

import androidx.lifecycle.MutableLiveData
import com.example.ranchratings_11.dto.Restaurant

/**
 * A service that will fetch restaurant data from a stream of live data.
 * This service is currently incomplete, and its current functionallity only exists for the purposes of being mocked in a unit test.
 */
class RestaurantService {
    internal fun fetchRestaurants(restaurantName: String) : MutableLiveData<ArrayList<Restaurant>> {
        val _restaurants = MutableLiveData<ArrayList<Restaurant>>()
        return _restaurants
    }
}