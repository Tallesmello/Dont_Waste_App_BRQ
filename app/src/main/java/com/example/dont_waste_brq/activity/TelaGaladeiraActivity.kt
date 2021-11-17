package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.cardview.widget.CardView
import androidx.core.view.size
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.databinding.ActivityTelaGaladeiraBinding
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.miguelcatalan.materialsearchview.MaterialSearchView

class TelaGaladeiraActivity : BaseActivity() {

    private lateinit var binding: ActivityTelaGaladeiraBinding
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaGaladeiraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        inicializaçãoComponentes()
        inicializaçãoToolbar()
        trocadeTelaItem()
        Listners()

        binding.cardViewFrutasVerduras.setOnClickListener {
            trocarTela(ItensFrutasActivity())
        }

        binding.btnVoltarGeladeira.setOnClickListener {
            trocarTela(TelaCategoriasActivity())
            finish()
        }

        binding.cardViewFrutasVerduras.setOnClickListener {
            trocarTela(ItensFrutasActivity())
            finish()
        }
    }

    private fun trocadeTelaItem(){
        binding.cardUmGeladeira.setOnClickListener {
            trocarTela(ItensFrutasActivity())
        }
    }
    /**
     * Icone home volta para Home Logada
     */
        private fun Listners() {
        binding.appBarTelaGeladeira.iconHome.setOnClickListener {
            trocarTela(HomeLogadaActivity())

        }
    }

    private fun inicializaçãoToolbar() {
        val toolbar = binding.appBarTelaGeladeira.toolbar
        toolbar.title = " "
        setSupportActionBar(toolbar)
    }

    /**
     * Inicialização da barra de pesquisa
     * em appBar
     */
    private fun inicializaçãoComponentes() {
        searchView = binding.appBarTelaGeladeira.searchView
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
