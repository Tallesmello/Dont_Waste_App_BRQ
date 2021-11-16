package com.example.dont_waste_brq.data

import android.widget.Toast
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//essa classe e do banco de dados
object RealtimeDatabase {
    /**
     * criando a instacia central do banco de dados
     */
    fun pegarInstancia(): DatabaseReference {
        return Firebase.database.reference
    }

    /**
     * salvando os dados de local e tipo em armazenamento
     */
    fun chaveCliente(): DatabaseReference {
        return pegarInstancia().child(FirebaseAuth.gerandoKeyDoUsuario())
    }

    fun listaDeItens(lista : ArrayList<ProdutoGeladeira>) : ArrayList<ProdutoGeladeira>{
        for (list in lista){
            lista.add(list)
        }
        return lista
    }
}