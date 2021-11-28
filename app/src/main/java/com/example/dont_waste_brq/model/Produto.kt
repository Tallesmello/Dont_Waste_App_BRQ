package com.example.dont_waste_brq.model

import java.io.Serializable

interface Produto : Serializable {
    val nome: String
    var quantidade: Int
    val data: String
}