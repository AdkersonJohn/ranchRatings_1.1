package com.example.ranchratings_11.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ranchratings_11.MainActivity
import com.example.ranchratings_11.R
import com.example.ranchratings_11.dto.Review
import kotlinx.android.synthetic.main.rating_fragment.*

class RatingFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = RatingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rating_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        btnBack.setOnClickListener {
            (activity as MainActivity).moveToMain()
        }
        btnSave.setOnClickListener {
            val review = Review().apply {
                reviewText = txtReview.text.toString()
                stars = ratingBar.rating.toDouble()
                userID
                institutionID
            }
            viewModel.save(review)
            txtReview.text.clear()
            ratingBar.rating = 0F
            (activity as MainActivity).moveToMain()
        }
    }
}