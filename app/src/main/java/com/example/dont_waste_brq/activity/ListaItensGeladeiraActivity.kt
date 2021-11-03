package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.size
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityListaItensGeladeiraBinding
import com.example.dont_waste_brq.databinding.ActivityTelaGaladeiraBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class ListaItensGeladeiraActivity : BaseActivity() {

    private lateinit var binding: ActivityListaItensGeladeiraBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaItensGeladeiraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaçãoComponentes()
        inicializaçãoToolbar()
        Listners()

        binding.btnVoltarItemFrutas.setOnClickListener {
            trocarTela(TelaGaladeiraActivity())
            finish()
        }
    }

    /**
     * Icone home volta para Home Logada
     */
    private fun Listners() {
        binding.appBarItemFrutasGeladeira.iconHome.setOnClickListener {
            trocarTela(HomeLogadaActivity())

        }
    }

    private fun inicializaçãoToolbar() {
        val toolbar = binding.appBarItemFrutasGeladeira.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)
    }

    /**
     * Inicialização da barra de pesquisa
     * em appBar
     */
    private fun inicializaçãoComponentes() {
        searchView = binding.appBarItemFrutasGeladeira.searchView
    }

    /**
     * Inflando icones em appBar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.icon_appbar, menu)

        /**
         * Configurar botão pesquisa (SearchView)
         */
        val item: MenuItem = menu!!.findItem(R.id.pesquisa)
        searchView.setMenuItem(item)
        val size = searchView.size


        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Colocando comportamentos no icone home e menu hamburguer
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.menu_hamburguer.apply {
            trocarTela(MenuHamburguer())
        }
        return super.onOptionsItemSelected(item)

    }
}
