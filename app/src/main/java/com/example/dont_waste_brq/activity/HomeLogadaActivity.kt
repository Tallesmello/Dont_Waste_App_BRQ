package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.size
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityHomeLogadaBinding
import com.example.dont_waste_brq.util.nextScreen
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
        setListners()

    }

    private fun setListners() {
        binding.toolbarHomelogada.iconHome.setOnClickListener {
            nextScreen(HomeLogadaActivity())
        }
        binding.btnDesperdiciosHomeLogada.setOnClickListener {
            nextScreen(DesperdiciosActivity())
            finish()
        }

        binding.btnVencimentosProximosHomeLogada.setOnClickListener {
            nextScreen(VencimentosProximosActivity())
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
        val size = searchView.size


        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Colocando comportamentos no icone menu hamburger
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.menu_hamburguer.apply {
            nextScreen(MenuHamburguer())
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}