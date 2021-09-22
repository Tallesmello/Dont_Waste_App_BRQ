package com.example.dont_waste_brq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.viewmodel.HomeNaoLogadaViewModel


class HomeNaoLogadaActivity : AppCompatActivity() {

    private lateinit var viewmodel : HomeNaoLogadaViewModel
    lateinit var btnPrimeiroAcesso: AppCompatButton
    lateinit var btnLoginHomeNaoLogada: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_nao_logada)

        btnPrimeiroAcesso = findViewById(R.id.btn_primeiro_acesso_hm_n_logada)
        btnLoginHomeNaoLogada = findViewById(R.id.btn_login_hm_n_logada)


        btnPrimeiroAcesso.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btnLoginHomeNaoLogada.setOnClickListener {
            val intentLogar = Intent(this, LoginActivity::class.java)
            startActivity(intentLogar)
        }
    }

}