package com.example.dont_waste_brq.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealTimeDatabase {
    private lateinit var database : DatabaseReference

    fun pegarInstancia(): DatabaseReference {
        return Firebase.database.reference
    }
}