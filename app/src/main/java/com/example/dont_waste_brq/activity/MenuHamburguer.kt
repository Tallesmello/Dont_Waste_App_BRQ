package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.databinding.ActivityMenuHamburguerBinding
import com.example.dont_waste_brq.util.nextScreen

class MenuHamburguer : BaseActivity() {

    lateinit var binding: ActivityMenuHamburguerBinding
    lateinit var auth: com.google.firebase.auth.FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuHamburguerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textCadastrar.setOnClickListener {
            nextScreen(TelaCategoriasActivity())
        }

        binding.imageSair.setOnClickListener {
            finish()
        }


        binding.textLogout.setOnClickListener {
            exibirDialog()
        }

    }


    private fun exibirDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Sair")
        dialog.setMessage("Deseja realmente sair?")
        dialog.setPositiveButton("Sair") { dialogInterface, i ->
            FirebaseAuth.deslogarApp()
            trocarTela(HomeNaoLogadaActivity())
            finish()

        }
        dialog.setNegativeButton("Cancelar") { dialogInterface, i ->
            trocarTela(this)
            finish()

        }
        dialog.create()
        dialog.show()
    }

}

