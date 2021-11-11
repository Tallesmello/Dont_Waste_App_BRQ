package com.example.dont_waste_brq.repository

import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoDispensa
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

interface Icrud {
    fun adicionarItens(produto : Produto)
    fun removerItem(id : Produto)

}