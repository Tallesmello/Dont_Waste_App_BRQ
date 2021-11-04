package com.example.dont_waste_brq.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealtimeDatabase {
    private lateinit var database : DatabaseReference

    fun PegarInstancia(): DatabaseReference {
        return Firebase.database.reference
    }

}