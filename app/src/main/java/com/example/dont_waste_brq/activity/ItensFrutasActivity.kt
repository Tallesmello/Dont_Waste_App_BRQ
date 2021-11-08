package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dont_waste_brq.activity.TelaGaladeiraActivity.TelaGeladeira.gerandoArmazenamento
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding
import com.example.dont_waste_brq.model.ConteudoInterno
import com.example.dont_waste_brq.model.TesteInclusao

class ItensFrutasActivity : AppCompatActivity() {

    private lateinit var binding : ActivityItensFrutasBinding
    private lateinit var conteudo: ConteudoInterno
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAlimentosCadastradosItemFrutas.setOnClickListener {
           RealtimeDatabase.enviandoAoBanco(armazenamento, gerandoConteudoInterno(TesteInclusao.conteudo))
        }


    }


    companion object TelaFrutas {
        val  FRUTAS = TipoConteudoEnum.FRUTAS
        val armazenamento = gerandoArmazenamento(FRUTAS)
        fun gerandoConteudoInterno(conteudo : ConteudoInterno): ConteudoInterno {
            conteudo.nome = TesteInclusao.conteudo.nome
            conteudo.quantidade = TesteInclusao.conteudo.quantidade
            conteudo.dataArmazenamento = TesteInclusao.conteudo.dataArmazenamento
            conteudo.dataFinal = TesteInclusao.conteudo.dataFinal
            return conteudo
        }
    }
}