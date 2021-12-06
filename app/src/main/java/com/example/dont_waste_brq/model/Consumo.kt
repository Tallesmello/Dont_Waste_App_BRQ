package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.EstadoEnum
import java.io.Serializable

data class Consumo(
    val quantidade: Int,
    val estado: EstadoEnum
) : Serializable
