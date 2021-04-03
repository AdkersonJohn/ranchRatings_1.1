package com.example.ranchratings_11.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ranchratings_11.dto.Review
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel : ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(review: Review) {
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