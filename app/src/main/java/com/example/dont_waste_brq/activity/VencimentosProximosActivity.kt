package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityProdutosProximosVencimentoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class VencimentosProximosActivity : BaseActivity() {
    private lateinit var binding : ActivityProdutosProximosVencimentoBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosProximosVencimentoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializarComponentes()

        val toolbar = binding.toolbarVencimentos.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)

        setListners()
    }

    private fun setListners() {
        binding.toolbarVencimentos.iconHome.setOnClickListener {
            trocarTela(HomeLogadaActivity())
            finish()
        }
    }

    private fun inicializarComponentes() {
        searchView = binding.toolbarVencimentos.searchView
    }


    /**
     * Criação do menu hamburguer em appBar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.icon_appbar, menu)

        /**
         * Configurar botão pesquisa (SearchView)
         */
        val item: MenuItem = menu!!.findItem(R.id.pesquisa)
        searchView.setMenuItem(item)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Configurar comportamentos do menu em appBar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.menu_hamburguer.apply {
            trocarTela(MenuHamburguer())
        }
        return super.onOptionsItemSelected(item)
    }

}