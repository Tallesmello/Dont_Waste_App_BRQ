package com.example.dont_waste_brq.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseRealtimeDatabase {

    private val reference = Firebase.database.reference
    fun pegarInstancia(): DatabaseReference {
        return reference
    }

}