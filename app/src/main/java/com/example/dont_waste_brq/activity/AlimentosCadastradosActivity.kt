package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.adapter.AlimentoCadastradoAdapter
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.databinding.ActivityAlimentosCadastradosBinding
import com.example.dont_waste_brq.databinding.ItemListProdutosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado

class AlimentosCadastradosActivity : BaseActivity() {

    private lateinit var binding: ActivityAlimentosCadastradosBinding
    private lateinit var alimentoAdapter : AlimentoCadastradoAdapter
    private val alimentos = ArrayList<AlimentoCadastrado>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlimentosCadastradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }




    private fun initRecyclerView() = with(binding) {
        alimentoAdapter = AlimentoCadastradoAdapter(alimentos)
        recyclerViewAlimentosCadastrados.adapter = alimentoAdapter
        }
    }

    fun recebeValores(){

    }