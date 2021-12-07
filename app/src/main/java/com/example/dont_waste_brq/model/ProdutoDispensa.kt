package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class ProdutoDispensa(
    override var nome: String = "",
    override var quantidade: Int = 0,
    override val data: String = gerarData(),
    override val consumo: ArrayList<Consumo>? = null
) : Produto {

    companion object Dispensa {
        val DISPENSA = LocalEnum.DISPENSA
        fun gerarData(): String {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            return currentDate
        }
    }
}