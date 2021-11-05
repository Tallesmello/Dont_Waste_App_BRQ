package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.model.ProdutoGeladeira

class ItemDAO {

    fun adiciona( item: ProdutoGeladeira) {
        itens.add(item)
    }

    fun buscaTodos() : List<ProdutoGeladeira> {
        return itens.toList()
    }

    companion object {
        private val itens = mutableListOf<ProdutoGeladeira>()
    }

}