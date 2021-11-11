package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.data.RealtimeDatabase

data class ProdutoDispensa(
    override var id : String = RealtimeDatabase.pegarInstancia().push().toString(),
    override var nome: String,
    override var quantidade: Int
) : Produto(id,nome,quantidade){

    companion object Dispensa {
        val DISPENSA = LocalEnum.DISPENSA
    }
}