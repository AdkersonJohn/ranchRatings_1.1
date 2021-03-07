package com.example.ranchratings_11.dto

import java.time.LocalDateTime

/**
 * A rating post for a restaurant's ranch, with its [rating] on a five-star scale,
 * its associated [text], the name of the [user] that posted it, and its [dateTime]
 */
data class RestaurantRating(var rating: Double, var text: String, var user: String, var dateTime: LocalDateTime) {
    /**
     * Returns a text-based representation of the RestaurantRating object in the format:
     *      [rating]: [text]
     *      Posted by [user] on [dateTime]
     */
    override fun toString(): String {
        return "$rating: $text\nPosted by $user on $dateTime"
    }
}