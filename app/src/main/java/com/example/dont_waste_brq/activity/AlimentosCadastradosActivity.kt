package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.adapter.AlimentoCadastradoAdapter
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.databinding.ActivityAlimentosCadastradosBinding
import com.example.dont_waste_brq.databinding.ItemListProdutosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado
import com.example.dont_waste_brq.model.Produto

class AlimentosCadastradosActivity : BaseActivity() {

    private lateinit var bindingItem: ItemListProdutosCadastradosBinding
    private lateinit var binding: ActivityAlimentosCadastradosBinding
    private lateinit var alimentoAdapter : AlimentoCadastradoAdapter
    private val alimentos = ArrayList<AlimentoCadastrado>()

    private val produto: Produto = intent.getSerializableExtra("produto") as Produto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlimentosCadastradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    fun passarDados() = with(bindingItem){
        val nome = produto.nome
        val quantidade = produto.quantidade
        val data = produto.data

        tvNomeAlimentoCadastrado.setText(nome)
        contadorItemFrutas.setText(quantidade)
        itemProdutosCadastradoData.setText(data)
    }


    private fun initRecyclerView() = with(binding) {
        alimentoAdapter = AlimentoCadastradoAdapter(alimentos)
        recyclerViewAlimentosCadastrados.adapter = alimentoAdapter
        }
    }

    fun recebeValores(){

    }