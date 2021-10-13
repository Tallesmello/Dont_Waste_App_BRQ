package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding

class DesperdiciosActivity : BaseActivity() {

    private lateinit var binding : ActivityRelatorioDeDesperdicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioDeDesperdicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDesoerdiciosVoltaLogin.setOnClickListener {
            trocarTela(HomeLogadaActivity())
        }

    }
}