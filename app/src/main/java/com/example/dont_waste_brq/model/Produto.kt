package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.util.JSONConvertable
import java.io.Serializable

interface Produto : Serializable, JSONConvertable {
    val nome: String
    var quantidade: Int
    val data: String
    val consumo: ArrayList<Consumo>?

    fun toAlimentoCadastrado(): AlimentoCadastrado = AlimentoCadastrado(
        this.nome,
        this.quantidade,
        this.quantidade,
        this.data,
        EstadoEnum.NEUTRO
    )

}