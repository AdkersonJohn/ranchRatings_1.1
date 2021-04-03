package com.example.ranchratings_11.dto

data class Institution(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val address: String,
    val image: String,
    var rating: Double,
    var isFavorite: Boolean
)