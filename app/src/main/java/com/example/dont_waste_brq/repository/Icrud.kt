package com.example.dont_waste_brq.repository

import com.example.dont_waste_brq.model.Produto
import com.google.android.gms.tasks.Task

interface Icrud {
    fun adicionarItens(produto : Produto)
    fun removerItem(id: String) : Task<Void>

}