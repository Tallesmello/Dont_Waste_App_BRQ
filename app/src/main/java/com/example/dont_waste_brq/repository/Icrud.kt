package com.example.dont_waste_brq.repository

import com.example.dont_waste_brq.model.Produto

interface Icrud {

    fun lerItens(result: (Boolean, String?, ArrayList<Produto?>?) -> Unit)
    fun adicionarItens(lista : ArrayList<Produto?>, result: (Boolean, String?) -> Unit)
    fun adicionarItem(produto : Produto)

}