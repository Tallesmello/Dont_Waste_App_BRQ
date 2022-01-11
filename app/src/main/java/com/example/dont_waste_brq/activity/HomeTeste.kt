package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.data.FirebaseRealtimeDatabase
import com.example.dont_waste_brq.databinding.ActivityHomeTesteBinding
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Consumo
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.util.toast

class HomeTeste : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeTesteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeTesteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lerDados()
    }

    private fun lerDados() {
        val dao = GeladeiraDAO(TipoConteudoEnum.FRUTAS)
//        dao.lerLocal{sucesso: Boolean, mensagem: String?, itens: ArrayList<Any>? ->
//            lerProdutos(sucesso, mensagem, itens)
//        }
    }

    private fun lerProdutos(sucesso: Boolean, mensagem: String?, itens: ArrayList<Any>?) {
        if (sucesso) {
            sucesso(itens)
        } else {
            toast("erro: $mensagem")
        }
    }

    private fun sucesso(itens: ArrayList<Any>?) {
        val produtos: ArrayList<Produto> = itens as ArrayList<Produto>
        println("sucessosucessosucesso")
        println(produtos.toString())
        toast("sucesso")
    }


    private fun adicionarDados() {
        val produtos = arrayListOf<Produto>(
            gerandoListaProdutosConsumido()
        )
        adicionarItens(
            Armazenar(LocalEnum.GELADEIRA, TipoConteudoEnum.FRUTAS), produtos) {
                sucesso: Boolean, mensagem: String? -> resultAdicao(sucesso, mensagem) }
    }

    private fun gerandoListaProdutosConsumido() = ProdutoGeladeira(
        "uva", 5,
        consumo = arrayListOf(
            Consumo(1, EstadoEnum.CONSUMIDO),
            Consumo(1, EstadoEnum.CONSUMIDO)
        )
    )

    private fun resultAdicao(sucesso: Boolean, mensagem: String?) {
        if (sucesso) {
            mensagem("sucesso")
        } else {
            mensagem("erro: $mensagem")
        }
    }

    fun adicionarItens(
        armazenar: Armazenar,
        lista: ArrayList<Produto>,
        result: (Boolean, String?) -> Unit
    ) {
        adicionandoItemNoBanco(armazenar, lista, result)
    }

    private fun adicionandoItemNoBanco(
        armazenar: Armazenar,
        lista: ArrayList<Produto>,
        result: (Boolean, String?) -> Unit
    ) {
        FirebaseRealtimeDatabase
            .pegarInstancia()
            .child(FirebaseAuth.gerandoKeyDoUsuario())
            .child(armazenar.local.toString())
            .child(armazenar.tipoConteudo.toString())
            .setValue(lista)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result(true, null)
                } else {
                    result(false, task.exception?.message)
                }
            }
    }


//        setSupportActionBar(binding.appBarHomeTeste.toolbar)
//
//        binding.appBarHomeTeste.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        val drawerLayout: DrawerLayout = binding.drawerLayout
//        val navView: NavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_content_home_teste)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.home_teste, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_home_teste)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}