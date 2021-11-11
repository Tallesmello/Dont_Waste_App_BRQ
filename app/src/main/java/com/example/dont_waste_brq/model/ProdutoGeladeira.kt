package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase

data class ProdutoGeladeira(
    override var id: String = RealtimeDatabase.pegarInstancia().push().toString(),
    override var nome: String,
    override var quantidade: Int
) : Produto(id,nome, quantidade) {


    companion object Geladeira {
        val Geladeira = LocalEnum.GELADEIRA
    }
}