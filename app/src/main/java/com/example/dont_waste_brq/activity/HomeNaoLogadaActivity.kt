package com.example.dont_waste_brq.activity

import android.os.Bundle
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class HomeNaoLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeNaoLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeNaoLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()
    }

    private fun initListeners() {
        binding.btnPrimeiroAcessoHmNLogada.setOnClickListener {
            trocarTela(PrimeiroAcessoActivity())
        }
        binding.btnLoginHmNLogada.setOnClickListener {
            trocarTela(LoginActivity())
        }
    }
}