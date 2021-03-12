package com.example.ranchratings_11

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranchratings_11.dto.Institution
import com.example.ranchratings_11.dto.Review
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel(){
    private var institutions: MutableLiveData<ArrayList<Institution>> = MutableLiveData<ArrayList<Institution>>()

    private lateinit var firestore : FirebaseFirestore

    init{

        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(review: Review){
        firestore.collection("reviews")
            .document()
            .set(review)
            .addOnSuccessListener {
                Log.d("Firebase", "Document Saved")
            }
            .addOnFailureListener {
                Log.d("Firebase", "Save Failed")
            }
    }

}