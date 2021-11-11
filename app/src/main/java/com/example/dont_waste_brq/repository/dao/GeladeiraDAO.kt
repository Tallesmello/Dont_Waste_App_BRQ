package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.Icrud
import com.google.firebase.database.DatabaseReference

class GeladeiraDAO(tipo : TipoConteudoEnum) : Icrud {
    private val armazenar = Armazenar(ProdutoGeladeira.Geladeira,tipo)
    override fun adicionarItens(produto: Produto) {
        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .child(produto.id)
            .setValue(produto.nome,produto.quantidade)    }

    override fun removerItem(id: Produto) {
        TODO("Not yet implemented")
    }


}