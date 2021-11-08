package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ConteudoInterno
import com.example.dont_waste_brq.model.TesteInclusao.armazenar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//essa classe e do banco de dados
object RealtimeDatabase {
    private lateinit var database : DatabaseReference
    private lateinit var conteudoInterno : ConteudoInterno

    /**
     * criando a instacia central do banco de dados
     */
    fun pegarInstancia(): DatabaseReference {
        return Firebase.database.reference
    }

    /**
     * salvando os dados de local e tipo em armazenamento
     */
    fun armazenandoLocal(armazenar: Armazenar): DatabaseReference {
        return pegarInstancia().child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(armazenar.local)
            .child(armazenar.tipoConteudo.toString())
    }
    fun enviandoAoBanco(armazenar: Armazenar,conteudo: ConteudoInterno){
        armazenandoLocal(armazenar).setValue(conteudo)
    }
}