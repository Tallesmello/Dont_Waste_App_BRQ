package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.data.FirebaseRealtimeDatabase
import com.example.dont_waste_brq.model.AlimentoCadastrado
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.android.gms.tasks.Task

class ConsumoGeladeiraDAO(val list: MutableList<AlimentoCadastrado>) :ItemDAO(Armazenar(ProdutoGeladeira.GELADEIRA)) {

    fun adicionarProutosComConsumo(result:(task : Task<Void>)-> Unit){
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(LocalEnum.CONSUMO.descricao)
            .setValue(list).addOnCompleteListener {task->
                if (task.isSuccessful){
                    result(task)
                }else{
                    result(task)
                }
            }
    }

}