package com.example.ranchratings_11.dto

data class Review (val userID : Int = 0, val institutionID : Int = 0, var reviewText : String = "", var stars : Double = 0.0){
}