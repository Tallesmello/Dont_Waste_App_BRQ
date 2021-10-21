package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.size
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityTelaCategoriasBinding
import com.example.dont_waste_brq.databinding.ActivityTelaGaladeiraBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class TelaCategoriasActivity : BaseActivity() {

    private lateinit var binding: ActivityTelaCategoriasBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCategoriasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaçãoComponentes()
        inicializaçãoToolbar()

        binding.imageDespensa.setOnClickListener{
            trocarTela(TelaDespensaActivity())
        }
    }

    private fun inicializaçãoToolbar() {
        val toolbar = binding.appBarTelaCategorias.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)
    }

    /**
     * Inicialização da barra de pesquisa
     * em appBar
     */
    private fun inicializaçãoComponentes() {
        searchView = binding.appBarTelaCategorias.searchView
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
            trocarTela(MenuHamburguer())
        }
        return super.onOptionsItemSelected(item)
    }
}