package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dont_waste_brq.activity.adapter.ProdutoGeladeiraAdapter
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding
import com.example.dont_waste_brq.model.ProdutoGeladeira

class ItensFrutasActivity : BaseActivity() {

    private lateinit var binding: ActivityItensFrutasBinding
    private lateinit var adapter: ProdutoGeladeiraAdapter

    val produtos = arrayListOf(
        ProdutoGeladeira("Banana"),
        ProdutoGeladeira("Maçã"),
        ProdutoGeladeira("Morango")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        voltarTelaGeladeiras()
        alimentosCadastrados()
        configuraRecyclerView()
        configurarListners()

    }

    private fun configurarListners() {
        binding.buttonNovoItem.setOnClickListener {
            if (binding.layoutNovoItem.visibility == View.GONE) {
                binding.buttonNovoItem.visibility = View.GONE
                binding.layoutNovoItem.visibility = View.VISIBLE
                binding.edittextNovoItem.requestFocus()
            } else {
                binding.layoutNovoItem.visibility = View.GONE
            }
        }

        binding.btnCancelarNovoItem.setOnClickListener {
            esconderLayoutNovoItem()
        }

        binding.btnIncluirNovoItem.setOnClickListener {
            if (binding.edittextNovoItem.text.trim().isNotEmpty()) {
                adapter.adicionarItem(
                    ProdutoGeladeira(
                        binding.edittextNovoItem.text.toString()
                    )
                )
                esconderLayoutNovoItem()
            } else {
                mensagem("Campo deve estar preenchido")
            }
        }
    }

    private fun esconderLayoutNovoItem() {
        binding.edittextNovoItem.setText("")
        binding.buttonNovoItem.visibility = View.VISIBLE
        binding.layoutNovoItem.visibility = View.GONE
    }

    private fun configuraRecyclerView() {
        adapter = ProdutoGeladeiraAdapter(produtos)
        binding.recyclerItens.adapter = adapter
        binding.recyclerItens.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        if (adapter.houveAtualizacao()) {
            sairSemSalvar()
        } else {
            super.onBackPressed()
        }
    }

    private fun sairSemSalvar(){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Atenção")
        dialog.setMessage("Itens atualizados não salvos. Sair sem salvar?")
        dialog.setPositiveButton("Sim") { dialogInterface, i ->
            finish()
        }
        dialog.setNegativeButton("Não") { dialogInterface, i ->
        }
        dialog.create()
        dialog.show()
    }

    private fun voltarTelaGeladeiras(){
        binding.btnVoltarItemFrutas.setOnClickListener {
            trocarTela(TelaGaladeiraActivity())
        }
    }

    private fun alimentosCadastrados(){
        binding.btnAlimentosCadastradosItemFrutas.setOnClickListener {
            trocarTela(AlimentosCadastradosActivity())
        }
    }
}