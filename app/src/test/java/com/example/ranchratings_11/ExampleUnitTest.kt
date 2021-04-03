package com.example.ranchratings_11

import com.example.ranchratings_11.dto.Institution
import com.example.ranchratings_11.dto.User
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun userDTO(){
        val user = User(id = 1, username = "JohnBoi48", password = "123456", image = "image.com")
        assertTrue(user.id == 1)
        assertTrue(user.username == "JohnBoi48")
        assertTrue(user.password == "123456")
    }
    @Test
    fun institutionDTO(){
        val institution = Institution(id = 4, name = "Macs", phoneNumber = "513-231-3213", image = "image.com", rating = 4.5, isFavorite = true, address = "666 Happy Lane")
        assertTrue(institution.isFavorite)
        assertTrue(institution.name == "Macs")
        assertTrue(institution.phoneNumber == "513-231-3213")
    }
}