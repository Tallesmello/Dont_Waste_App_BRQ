package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.EstadoEnum
import java.io.Serializable

interface Produto : Serializable {
    val nome: String
    var quantidade: Int
    val data: String

    fun toAlimentoCadastrado(): AlimentoCadastrado = AlimentoCadastrado(
        this.nome,
        this.quantidade,
        this.data,
        EstadoEnum.NEUTRO
    )

}