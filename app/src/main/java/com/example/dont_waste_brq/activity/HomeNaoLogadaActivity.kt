package com.example.dont_waste_brq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding


class HomeNaoLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeNaoLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeNaoLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListners()
    }

    private fun setupListners() {
        binding.btnPrimeiroAcessoHmNLogada.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoginHmNLogada.setOnClickListener {
            val intentLogar = Intent(this, LoginActivity::class.java)
            startActivity(intentLogar)
        }
    }

}