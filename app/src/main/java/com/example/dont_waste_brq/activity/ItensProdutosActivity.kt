package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityItensProdutosBinding
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.DispensaDAO
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.repository.dao.ItemDAO

class ItensProdutosActivity : BaseActivity() {

    private lateinit var binding: ActivityItensProdutosBinding
    private lateinit var adapter: ProdutoAdapter

    private lateinit var dao: ItemDAO

    private val produtos = ArrayList<Produto?>()

    private lateinit var local: LocalEnum
    private lateinit var conteudo: TipoConteudoEnum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLocalConteudo()
        setDao()
        setSubTitulo()
        configurarListners()
        lerProdutos()
    }

    private fun setLocalConteudo() {
        val iLocal = intent.getIntExtra(LOCAL, 0)
        val iConteudo = intent.getIntExtra(CONTEUDO, 0)
        local = LocalEnum.values()[iLocal]
        conteudo = TipoConteudoEnum.values()[iConteudo]


    }

    private fun setSubTitulo() {
        binding.textItemProduto.text = conteudo.descricao
    }

    private fun setDao() {
        if (local == LocalEnum.DISPENSA) {
            dao = DispensaDAO(conteudo)
        } else if (local == LocalEnum.GELADEIRA) {
            dao = GeladeiraDAO(conteudo)
        }
    }

    private fun lerProdutos() {
        showProgressBar()
        dao.lerItens { ok: Boolean, mensagemErro: String?, itens: ArrayList<Produto?>? ->
            lerProdutosResult(ok, mensagemErro, itens)
        }
    }

    private fun showProgressBar() {
        binding.recyclerItens.visibility = View.GONE
        binding.progressbarProdutos.visibility = View.VISIBLE
    }

    private fun showRecycler() {
        binding.recyclerItens.visibility = View.VISIBLE
        binding.progressbarProdutos.visibility = View.GONE
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
        showRecycler()
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
            showProgressBar()
            dao.adicionarItens(produtos){
                sucesso: Boolean, mensagemErro: String? ->
                salvarStatus(sucesso, mensagemErro)
            }
        }

        binding.btnVoltarItemFrutas.setOnClickListener {
            if (adapter.houveAtualizacao()) {
                sairSemSalvar()
            } else {
                finish()
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
        showRecycler()
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

    companion object {

        private const val LOCAL = "local"
        private const val CONTEUDO = "conteudo"

        fun getIntent(
            _context: Context,
            _local: LocalEnum,
            _conteudo: TipoConteudoEnum
        ): Intent {
            val intent = Intent(_context, ItensProdutosActivity::class.java)
            intent.putExtra(LOCAL, _local.ordinal).putExtra(CONTEUDO, _conteudo.ordinal)
            return intent
        }

    }
}