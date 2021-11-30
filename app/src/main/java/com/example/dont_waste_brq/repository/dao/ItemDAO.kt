package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.data.FirebaseRealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.Icrud

abstract class ItemDAO(private val armazenar: Armazenar): Icrud {

    override fun lerLocal(result: (Boolean, String?, ArrayList<Produto>?) -> Unit) {
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val produtos = ArrayList<Produto>()
                    val result = task.result
                    result?.let {
                        val itens = it.children.map {
                            it.getValue(ProdutoGeladeira::class.java)!!
                        }
                        produtos.addAll(itens)
                    }
                    result(true, null, produtos)
                } else {
                    result(false, task.exception?.message, null)
                }
            }
    }

    override fun lerItens(
        result: (Boolean, String?, ArrayList<Produto?>?) -> Unit
    ) {
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val produtos = ArrayList<Produto?>()
                    val result = task.result
                    result?.let {
                        val itens = it.children.map {
                            it.getValue(ProdutoGeladeira::class.java)!!
                        }
                        produtos.addAll(itens)
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
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
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
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .push()
            .setValue(produto.nome,produto.quantidade)
    }

}