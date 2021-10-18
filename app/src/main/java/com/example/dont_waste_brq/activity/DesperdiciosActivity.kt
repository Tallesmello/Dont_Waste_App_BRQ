package com.example.dont_waste_brq.activity

import com.example.dont_waste_brq.activity.BaseActivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.widget.ToolbarWidgetWrapper
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.PrimeiroAcessoActivity
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.example.dont_waste_brq.activity.VencimentosProximosActivity as VencimentosProximosActivity

class DesperdiciosActivity : BaseActivity() {

    private lateinit var binding: ActivityRelatorioDeDesperdicioBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioDeDesperdicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializarComponentes()

        val toolbar = binding.toolbarDesperdicios.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)


    }

    private fun inicializarComponentes() {
        searchView = binding.toolbarDesperdicios.searchView
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
        R.id.action_settings.apply {
            trocarTela(ActivityMenu())
        }
        return super.onOptionsItemSelected(item)
    }


}