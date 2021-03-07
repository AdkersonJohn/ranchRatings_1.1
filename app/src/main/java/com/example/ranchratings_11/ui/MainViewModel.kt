package com.example.ranchratings_11.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranchratings_11.dto.Restaurant
import com.example.ranchratings_11.service.RestaurantService

class MainViewModel : ViewModel() {
    var restaurants: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    var restaurantService: RestaurantService = RestaurantService()

    fun fetchRestaurants(restaurantName: String) {
        restaurants = restaurantService.fetchRestaurants(restaurantName)
    }
}