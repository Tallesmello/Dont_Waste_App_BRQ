package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding
import com.example.dont_waste_brq.util.nextScreen


class HomeNaoLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeNaoLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeNaoLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()
        verificandoUsuarioLogado()
    }

    private fun verificandoUsuarioLogado() {
        if (FirebaseAuth.usuarioLogado()) {
            nextScreen(HomeLogadaActivity())
            finish()
        }
    }

    private fun initListeners() {
        binding.btnPrimeiroAcessoHmNLogada.setOnClickListener {
            nextScreen(PrimeiroAcessoActivity())
        }
        binding.btnLoginHmNLogada.setOnClickListener {
            nextScreen(LoginActivity())
        }
    }
}