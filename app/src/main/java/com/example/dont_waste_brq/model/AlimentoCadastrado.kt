package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.EstadoEnum

data class AlimentoCadastrado(
    var nome : String,
    var quantidade: Int,
    val data: String,
    val estado: EstadoEnum
)
