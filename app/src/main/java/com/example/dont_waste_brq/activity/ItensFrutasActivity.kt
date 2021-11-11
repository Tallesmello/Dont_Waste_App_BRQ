package com.example.dont_waste_brq.activity

import android.os.Bundle
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO

class ItensFrutasActivity : BaseActivity() {

    private lateinit var binding : ActivityItensFrutasBinding
    private lateinit var dao : GeladeiraDAO
    private lateinit var prod : Produto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dao = GeladeiraDAO(TipoConteudoEnum.FRUTAS)
        prod = ProdutoGeladeira(nome ="maaca",quantidade = 10)
        binding.btnAlimentosCadastradosItemFrutas.setOnClickListener {
            dao.adicionarItens(prod)
        }
    }

}