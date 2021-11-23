package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum

data class ProdutoDispensa(
    override var nome: String = "",
    override var quantidade: Int = 0
) : Produto {

    companion object Dispensa {
        val DISPENSA = LocalEnum.DISPENSA
    }
}