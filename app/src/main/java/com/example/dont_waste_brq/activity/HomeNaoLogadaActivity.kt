package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding




class HomeNaoLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeNaoLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeNaoLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()

        if (FirebaseAuth.usuarioLogado()) {
            startActivity(Intent(this, HomeLogadaActivity::class.java))
            finish()
        }

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