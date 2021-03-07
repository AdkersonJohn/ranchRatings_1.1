package com.example.ranchratings_11.dto

import java.time.LocalDateTime
import kotlin.math.round

/**
 * A Restaurant with a [name] and its list of [ratings]
 */
data class Restaurant (var name: String, var ratings: ArrayList<RestaurantRating> = ArrayList()) {
    /**
     * Posts a rating with it's [rating] on a 5-star scale, its associated [text],
     * the name of the [user] who posted it, and its [dateTime] to the list of [ratings]
     */
    fun postReview(rating: Double, text: String, user: String, dateTime: LocalDateTime) {
        ratings.add(RestaurantRating(rating, text, user, dateTime))
    }

    /**
     * Returns the average rating rounded to the nearest half of a star from the list of [ratings]
     */
    fun ratingAverageRoundedToHalf(): Double {
        var sum = 0.0
        ratings.forEach {
            sum += it.rating
        }
        return round(sum * 2 / ratings.size) / 2
    }

    /**
     * returns a text based representation of the restaurant object, in the format:
     *      [name]:
     *      [rating 1]
     *      [rating 2]
     *      ...
     *      [rating n]
     */
    override fun toString(): String {
        var text = "$name:"
        ratings.forEach {
            text += "\n" + it.toString()
        }
        return text
    }
}