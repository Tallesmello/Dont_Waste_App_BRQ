package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.adapter.AlimentoCadastradoAdapter
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityAlimentosCadastradosBinding
import com.example.dont_waste_brq.databinding.ItemListProdutosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.repository.dao.ItemDAO

class AlimentosCadastradosActivity : BaseActivity() {

    private lateinit var binding: ActivityAlimentosCadastradosBinding
    private lateinit var alimentoAdapter : AlimentoCadastradoAdapter
    private val alimentos = ArrayList<AlimentoCadastrado>()

    private lateinit var dao: ItemDAO

    private val produtos = ArrayList<Produto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlimentosCadastradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lerLocal()
    }

    private fun lerLocal() {
        var continuar = true
        produtos.clear()
        alimentos.clear()
        var contador = 1
        for (conteudo in TipoConteudoEnum.values()) {
            dao = GeladeiraDAO(conteudo)
            dao.lerLocal { ok: Boolean, mensagemErro: String?, itens: ArrayList<Produto>? ->
                lerProdutosResult(ok, mensagemErro, itens, contador++)
                continuar = ok
            }
            if (!continuar) {
                mensagem("Problemas ao ler ${conteudo.descricao}")
                break
            }
        }
    }

    private fun lerProdutosResult(
        sucesso: Boolean, mensagemErro: String?, itens: ArrayList<Produto>?, contador: Int
    ) {
        if (sucesso) {
            if (itens.isNullOrEmpty()) {
                mensagem("Nenhum produto lido")
            } else {
                produtos.addAll(itens)
                alimentos.addAll(itens.map { it.let { it.toAlimentoCadastrado() } })
            }
            //configuraRecyclerView()
        } else {
            mensagem("Erro ao ler produtos: $mensagemErro")
        }
        if (contador == TipoConteudoEnum.values().size) {
            initRecyclerView()
        }
        //showRecycler()
    }



    private fun initRecyclerView() = with(binding) {
        alimentoAdapter = AlimentoCadastradoAdapter(alimentos)
        recyclerViewAlimentosCadastrados.adapter = alimentoAdapter
        recyclerViewAlimentosCadastrados.layoutManager = LinearLayoutManager(this@AlimentosCadastradosActivity)
        }
    }

    fun recebeValores(){

    }