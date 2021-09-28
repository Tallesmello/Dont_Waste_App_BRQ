package com.example.dont_waste_brq.domain.model

data class Result (
    val success: Boolean = false,
    val errorMessage: String? = ""
)