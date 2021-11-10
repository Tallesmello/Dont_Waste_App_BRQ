package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.Icrud
class GeladeiraDAO(tipo : TipoConteudoEnum) : Icrud {
    private val armazenar = Armazenar(ProdutoGeladeira.GELADEIRA,tipo)

    override fun adicionarItens(produto : ProdutoGeladeira) {
        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local)
            .child(armazenar.tipoConteudo.toString())
            .push()
            .setValue(produto)
    }

    override fun removerItem() {
        TODO("Not yet implemented")
    }


}