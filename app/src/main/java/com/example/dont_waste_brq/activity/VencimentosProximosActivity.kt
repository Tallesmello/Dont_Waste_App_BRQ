package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.databinding.ActivityProdutosProximosVencimentoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding

class VencimentosProximosActivity : BaseActivity() {
    private lateinit var binding : ActivityProdutosProximosVencimentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosProximosVencimentoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnVencimentosVoltaLogin.setOnClickListener {
            trocarTela(HomeLogadaActivity())
            finish()
        }
    }
}