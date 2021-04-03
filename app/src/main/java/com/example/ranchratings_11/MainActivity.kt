package com.example.ranchratings_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ranchratings_11.ui.main.MainFragment
import com.example.ranchratings_11.ui.main.RatingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var ratingFragment: RatingFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainFragment = MainFragment.newInstance()
        ratingFragment = RatingFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
            activeFragment = mainFragment
        }
    }

    internal fun moveToMain() {
        if (activeFragment != mainFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
            activeFragment = mainFragment
        }
    }

    internal fun moveToRating() {
        if (activeFragment != ratingFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ratingFragment)
                .commitNow()
            activeFragment = ratingFragment
        }
    }
}