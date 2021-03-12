package com.example.ranchratings_11

import android.os.Bundle
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.ranchratings_11.dto.Review
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var viewModel: MainViewModel
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)

        var submitButton = findViewById<ImageButton>(R.id.btnSubmit)
        submitButton.setOnClickListener(){
            enterReviewLayout()
        }


}
        fun enterReviewLayout(){
            setContentView(R.layout.rating_fragment)
            var saveButton = findViewById<ImageButton>(R.id.btnSave)
            saveButton.setOnClickListener(){
                var review = Review().apply{
                    reviewText = findViewById<TextView>(R.id.txtReview).text.toString()
                    stars = findViewById<RatingBar>(R.id.ratingBar).rating.toDouble()
                    userID
                    institutionID
                    //TODO save this object

                }
                viewModel.save(review)
                setContentView(R.layout.rating_fragment)
            }

         }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }






}
