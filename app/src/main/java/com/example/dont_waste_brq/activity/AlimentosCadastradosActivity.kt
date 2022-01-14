package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dont_waste_brq.activity.adapter.AlimentoCadastradoAdapter
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityAlimentosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado
import com.example.dont_waste_brq.model.Consumo
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.repository.dao.ItemDAO

class AlimentosCadastradosActivity : BaseActivity() {

    private lateinit var binding: ActivityAlimentosCadastradosBinding
    private lateinit var alimentoAdapter: AlimentoCadastradoAdapter
    private val alimentos = ArrayList<AlimentoCadastrado>()
    private lateinit var dao: ItemDAO
    private lateinit var tipoConteudo: TipoConteudoEnum
    private val produtos = ArrayList<ProdutoGeladeira>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlimentosCadastradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBinding()
        getValues()
    }

    private fun setBinding() {
        binding.btnSalvarAlimentosCadastrados.setOnClickListener {
            salvarAlimentos()
        }
        binding.btnVoltarAlimentosCadastrados.setOnClickListener {
            fim()
        }
    }

    private fun fim() {
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun getValues() {
        produtos.clear()
        alimentos.clear()
        intent.let {
            tipoConteudo = TipoConteudoEnum.values()[it.getIntExtra(TIPO_CONTEUDO, 0)]
            try {
                val _produtos = it.getSerializableExtra(PRODUTOS) as ArrayList<ProdutoGeladeira>
                if (!_produtos.isNullOrEmpty()) {
                    produtos.addAll(_produtos)
                    alimentos.addAll(_produtos.map { it.toAlimentoCadastrado() })
                }
            }catch (e : NullPointerException){
                e.printStackTrace()
            }
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        alimentoAdapter = AlimentoCadastradoAdapter(alimentos)
        binding.recyclerViewAlimentosCadastrados.adapter = alimentoAdapter
        binding.recyclerViewAlimentosCadastrados.layoutManager =
            LinearLayoutManager(this@AlimentosCadastradosActivity)
    }

    private fun salvarAlimentos() {
        var ok = false
        for (i in 0 until alimentos.size) {
            if (verificaSeAlimentoEVazioOuNeutro(i)) {
                if (produtos[i].consumo == null) {
                    produtos[i].consumo = arrayListOf()
                }
                produtos[i].consumo?.add(
                    Consumo(
                        alimentos[i].quantidade,
                        alimentos[i].estado
                    )
                )
                produtos[i].quantidade -= alimentos[i].quantidade
                ok = true
            }
        }
        if (ok) {
            salvar()
        } else {
            fim()
        }
    }

    private fun verificaSeAlimentoEVazioOuNeutro(i: Int) =
        alimentos[i].quantidade > 0 && alimentos[i].estado != EstadoEnum.NEUTRO

    private fun salvar() {
        dao = GeladeiraDAO(tipoConteudo)
        dao.adicionarItens(produtos) { sucesso: Boolean, mensagem: String? ->
            resultAdicao(sucesso, mensagem)
        }
    }

    private fun resultAdicao(sucesso: Boolean, mensagem: String?) {
        if (sucesso) {
            mensagem("sucesso ao salvar alimentos")
            setResult(RESULT_OK, Intent().putExtra(PRODUTOS, produtos))
            finish()

        } else {
            mensagem("erro: $mensagem")
        }
    }

    companion object {

        const val PRODUTOS = "produtos"
        private const val TIPO_CONTEUDO = "tipo conteudo"

        fun getIntent(
            _context: Context,
            tipoConteudo: TipoConteudoEnum,
            produtos: ArrayList<ProdutoGeladeira>
        ) = Intent(_context, AlimentosCadastradosActivity::class.java)
            .putExtra(PRODUTOS, produtos)
            .putExtra(TIPO_CONTEUDO, tipoConteudo.ordinal)
    }
}


