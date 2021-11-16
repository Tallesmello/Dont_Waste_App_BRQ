package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.Icrud
import com.google.android.gms.tasks.Task

class GeladeiraDAO(tipo : TipoConteudoEnum) : Icrud {
    private val armazenar = Armazenar(ProdutoGeladeira.GELADEIRA,tipo)
    override fun adicionarItens(produto: Produto) {

        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .push()
            .setValue(produto.nome,produto.quantidade)    }

    override fun removerItem(key: String): Task<Void> {
        return RealtimeDatabase.pegarInstancia().child(key).removeValue();
    }
}