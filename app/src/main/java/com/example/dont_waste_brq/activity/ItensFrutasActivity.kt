package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO

class ItensFrutasActivity : BaseActivity() {

    private lateinit var binding: ActivityItensFrutasBinding
    private lateinit var adapter: ProdutoAdapter

    private val dao = GeladeiraDAO(TipoConteudoEnum.FRUTAS)

    private val produtos = ArrayList<Produto?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        voltarTelaGeladeiras()
        alimentosCadastrados()

        configurarListners()
        lerProdutos()

    }

    private fun lerProdutos() {
        dao.lerItens { ok: Boolean, mensagemErro: String?, itens: ArrayList<Produto?>? ->
            lerProdutosResult(ok, mensagemErro, itens)
        }
    }

    private fun lerProdutosResult(
        sucesso: Boolean, mensagemErro: String?, itens: ArrayList<Produto?>?
    ) {
        if (sucesso) {
            if (itens.isNullOrEmpty()) {
                mensagem("Nenhum produto lido")
            } else {
                produtos.addAll(itens)
            }
            configuraRecyclerView()
        } else {
            mensagem("Erro ao ler produtos: $mensagemErro")
        }
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

        binding.btnAlimentosCadastradosItemFrutas.setOnClickListener {
            dao.adicionarItens(produtos){
                sucesso: Boolean, mensagemErro: String? ->
                salvarStatus(sucesso, mensagemErro)
            }
        }
    }

    private fun salvarStatus(sucesso: Boolean, mensagemErro: String?){
        if (sucesso) {
            mensagem("Produtos salvos")
            adapter.resetAtualizacoes()
        } else {
            mensagem("Erro ao salvar produtos\n$mensagemErro")
        }
    }

    private fun esconderLayoutNovoItem() {
        binding.edittextNovoItem.setText("")
        binding.buttonNovoItem.visibility = View.VISIBLE
        binding.layoutNovoItem.visibility = View.GONE
    }

    private fun configuraRecyclerView() {
        adapter = ProdutoAdapter(produtos)
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
        dialog.setPositiveButton("Sim") { _, _ ->
            finish()
        }
        dialog.setNegativeButton("Não") { _, _ ->
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

    companion object {

        fun getInstance(
            local: LocalEnum,
            conteudo: TipoConteudoEnum
        ) {

        }

    }
}