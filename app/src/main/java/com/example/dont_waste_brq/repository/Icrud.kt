package com.example.dont_waste_brq.repository

import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.android.gms.tasks.Task

interface Icrud {

    fun lerItens(result: (Boolean, String?, ArrayList<Produto>?) -> Unit)
    fun lerLocal(result: (Boolean, String?, String?) -> Unit)
    fun adicionarItens(lista : ArrayList<ProdutoGeladeira>, result: (Boolean, String?) -> Unit)
    fun adicionarItem(produto : Produto)

}