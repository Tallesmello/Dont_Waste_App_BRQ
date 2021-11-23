package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum

data class ProdutoGeladeira(
    override var nome: String,
    override var quantidade: Int =0
) : Produto(nome,quantidade){

    companion object Geladeira {
        val GELADEIRA = LocalEnum.GELADEIRA
    }
}