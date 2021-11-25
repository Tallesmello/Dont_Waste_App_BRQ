package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.EstadoEnum

data class AlimentoCadastrado(
    var nome : String,
    val quantidade: String,
    val data: String,
    val estado: EstadoEnum
)
