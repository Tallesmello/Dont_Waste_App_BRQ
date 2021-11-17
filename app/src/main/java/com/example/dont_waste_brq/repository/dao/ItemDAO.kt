package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.repository.Icrud
import com.google.android.gms.tasks.Task

abstract class ItemDAO(private val armazenar: Armazenar): Icrud {

    override fun lerItens(
        result: (Boolean, String?, ArrayList<Produto?>?) -> Unit
    ) {
        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val produtos = ArrayList<Produto?>()
                    val result = task.result
                    result?.let {
                        it.children.forEach {
                            val produto: Produto? = it.getValue(Produto::class.java)
                            produtos.add(produto)
                        }
                    }
                    result(true, null, produtos)
                } else {
                    result(false, task.exception?.message, null)
                }
            }
    }

    override fun adicionarItens(
        lista: ArrayList<Produto?>,
        result: (Boolean, String?) -> Unit
    ) {
        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .setValue(lista)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result(true, null)
                } else {
                    result(false, task.exception?.message)
                }
            }
    }

    override fun adicionarItem(produto: Produto) {
        RealtimeDatabase
            .chaveCliente()
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .push()
            .setValue(produto.nome,produto.quantidade)
    }

    override fun removerItem(produto: Produto): Task<Void> {
        return RealtimeDatabase.pegarInstancia().child(produto.toString()).removeValue();
    }

}