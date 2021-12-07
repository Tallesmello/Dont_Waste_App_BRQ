package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class ProdutoGeladeira(
    override var nome: String = "",
    override var quantidade: Int =0,
    override val data: String = gerarData(),
    override var consumo: ArrayList<Consumo>? = null
) : Produto {

    companion object Geladeira {
        val GELADEIRA = LocalEnum.GELADEIRA
        fun gerarData(): String {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            return currentDate
        }
    }

}