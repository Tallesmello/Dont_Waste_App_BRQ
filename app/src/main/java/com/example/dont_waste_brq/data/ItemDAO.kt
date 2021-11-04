package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.model.Itens

class ItemDAO {

    fun adiciona( item: Itens) {
        itens.add(item)
    }

    fun buscaTodos() : List<Itens> {
        return itens.toList()
    }

    companion object {
        private val itens = mutableListOf<Itens>()
    }

}