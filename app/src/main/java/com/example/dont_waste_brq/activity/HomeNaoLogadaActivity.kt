package com.example.dont_waste_brq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding
import com.example.dont_waste_brq.viewmodel.HomeNaoLogadaViewModel


class HomeNaoLogadaActivity : AppCompatActivity() {

    private lateinit var viewmodel : HomeNaoLogadaViewModel
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