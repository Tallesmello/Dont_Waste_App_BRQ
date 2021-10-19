package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityHomeLogadaBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class HomeLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeLogadaBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaçãoToolbar()
        inicializaçãoComponentes()

        binding.btnDesperdiciosHomeLogada.setOnClickListener {
            trocarTela(DesperdiciosActivity())
            finish()
        }

        binding.btnVencimentosProximosHomeLogada.setOnClickListener {
            trocarTela(VencimentosProximosActivity())
            finish()
        }


    }

    private fun inicializaçãoToolbar() {
        val toolbar = binding.toolbarHomelogada.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)
    }

    /**
     * Inicialização da barra de pesquisa
     * em appBar
     */
    private fun inicializaçãoComponentes() {
        searchView = binding.toolbarHomelogada.searchView
    }


    /**
     * Inflando icones em appBar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.icon_appbar, menu)

        /**
         * Configurar botão pesquisa (SearchView)
         */
        val item : MenuItem = menu!!.findItem(R.id.pesquisa)
        searchView.setMenuItem(item)

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Colocando comportamentos no icone menu hamburger
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.menu_hamburguer.apply {
            trocarTela(MenuHamburguer())
        }
        return super.onOptionsItemSelected(item)
    }
}