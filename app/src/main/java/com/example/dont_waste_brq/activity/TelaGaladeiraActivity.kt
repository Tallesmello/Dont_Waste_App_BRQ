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
import com.miguelcatalan.materialsearchview.MaterialSearchView

class TelaGaladeiraActivity : BaseActivity() {

    private lateinit var binding: ActivityTelaGaladeiraBinding
    private lateinit var searchView: MaterialSearchView

    private lateinit var resultado : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaGaladeiraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inicializaçãoComponentes()
        inicializaçãoToolbar()
        Listners()
        clickCards(binding.cardViewFrutasVerduras)
        binding.btnVoltarGeladeira.setOnClickListener {
            trocarTela(TelaCategoriasActivity())
            finish()
        }
    }

    private fun clickCards(card : CardView) {
        card.setOnClickListener {
            gerandoArmazenamento(TipoConteudoEnum.FRUTAS)
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


    companion object TelaGeladeira{
        val  local = LocalEnum.GELADEIRA
        private lateinit var armazenar : Armazenar
        fun gerandoArmazenamento(tipo: TipoConteudoEnum): Armazenar {
            armazenar.local = local.descricao
            armazenar.tipoConteudo.descricao = tipo.descricao
            return armazenar
        }


    }

}
