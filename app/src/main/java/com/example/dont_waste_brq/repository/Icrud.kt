package com.example.dont_waste_brq.repository

import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.android.gms.tasks.Task

interface Icrud {
    fun adicionarItens(produto : ProdutoGeladeira)

    fun removerItem()

}