package com.example.dont_waste_brq.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dont_waste_brq.activity.AlimentosCadastradosActivity.Companion.PRODUTOS
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityItensProdutosBinding
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.DispensaDAO
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.repository.dao.ItemDAO
import com.example.dont_waste_brq.util.hide
import com.example.dont_waste_brq.util.nextScreen
import com.example.dont_waste_brq.util.show
import com.example.dont_waste_brq.util.snackbar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItensProdutosActivity : BaseActivity() {

    private lateinit var binding: ActivityItensProdutosBinding
    private lateinit var adapter: ProdutoAdapter

    private lateinit var dao: ItemDAO

    private val produtos = ArrayList<ProdutoGeladeira>()

    private lateinit var local: LocalEnum
    private lateinit var conteudo: TipoConteudoEnum

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLocalConteudo()
        setDao()
        setSubTitulo()
        configurarListners()
        configurarLauncher()
        lerProdutos()
    }

    private fun configurarLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                validandoProdutos(result)
            }
        }
    }

    private fun validandoProdutos(result: ActivityResult) {
        val _produtos = result.data
            ?.getSerializableExtra(PRODUTOS) as ArrayList<ProdutoGeladeira>
        if (!_produtos.isNullOrEmpty()) {
            adicionandoSeNaoForNulo(_produtos)
        }
    }

    private fun adicionandoSeNaoForNulo(_produtos: ArrayList<ProdutoGeladeira>) {
        produtos.clear()
        produtos.addAll(_produtos)
        configuraRecyclerView()
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
        dao.lerLocal {sucesso: Boolean, mensagem: String?, json: String? ->
            lerProdutosResult(sucesso, mensagem, json)}
    }

    private fun showProgressBar() {
        binding.recyclerItens.hide()
        binding.progressbarProdutos.show()
    }

    private fun showRecycler() {
        binding.recyclerItens.show()
        binding.progressbarProdutos.hide()
    }

    private fun lerProdutosResult(
        sucesso: Boolean, mensagemErro: String?, json: String?
    ) {
        if (sucesso) {
            if (json == null) {
                mensagem("Nenhum produto lido")
            } else {
                lendoProdutos(json)
            }
            configuraRecyclerView()
        } else {
            mensagem("Erro ao ler produtos: $mensagemErro")
        }
        showRecycler()
    }

    private fun lendoProdutos(json: String?) {
        val gson = Gson()
        val itemType = object : TypeToken<List<ProdutoGeladeira>>() {}.type
        val itens = gson.fromJson<List<ProdutoGeladeira>>(json, itemType)

        produtos.addAll(itens)
    }

    private fun configurarListners() = with(binding) {
        buttonNovoItem.setOnClickListener {
            abrindoNovoItem()
        }

        btnCancelarNovoItem.setOnClickListener {
            esconderLayoutNovoItem()
        }

        btnIncluirNovoItem.setOnClickListener {
            confirmandoInclusaoDoNovoItem()
        }

        btnAlimentosCadastradosItemFrutas.setOnClickListener {
            salvarStatusDoAlimentoCadastrado()
        }
        btnVoltarItemFrutas.setOnClickListener {
            sair()
        }
    }

    private fun sair() {
        if (adapter.houveAtualizacao()) {
            sairSemSalvar()
        } else {
            finish()
        }
    }

    private fun salvarStatusDoAlimentoCadastrado() {
        showProgressBar()
        enviandoAoBanco()
        nextScreen(AlimentosCadastradosActivity())
        var ok = false
        ok = verificandoQuantidadeDeItens(ok)
        result(ok)
    }

    private fun result(ok: Boolean) {
        if (ok) {
            chamarAlimentosCadastrados()
        } else {
            snackbar(binding.containerGeladeira, "Sem alimentos para consumir")
        }
    }

    private fun verificandoQuantidadeDeItens(ok: Boolean): Boolean {
        var ok1 = ok
        if (!produtos.isNullOrEmpty()) {
            for (prod in produtos) {
                if (prod.quantidade > 0) {
                    ok1 = true
                }
            }
        }
        return ok1
    }

    private fun enviandoAoBanco() {
        dao.adicionarItens(produtos) { sucesso: Boolean, mensagemErro: String? ->
            salvarStatus(sucesso, mensagemErro)
        }
    }

    private fun confirmandoInclusaoDoNovoItem() {
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

    private fun abrindoNovoItem() {
        if (binding.layoutNovoItem.visibility == View.GONE) {
            binding.buttonNovoItem.hide()
            binding.layoutNovoItem.show()
            binding.edittextNovoItem.requestFocus()
        } else {
            binding.layoutNovoItem.hide()
        }
    }

    private fun chamarAlimentosCadastrados() {
        val intent = AlimentosCadastradosActivity.getIntent(this, conteudo, produtos)
        resultLauncher.launch(intent)
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
    //remover mais tarde
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