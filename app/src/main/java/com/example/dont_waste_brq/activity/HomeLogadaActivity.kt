package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityHomeLogadaBinding
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.google.android.material.appbar.AppBarLayout

class HomeLogadaActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeLogadaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDesperdiciosHomeLogada.setOnClickListener {
            trocarTela(DesperdiciosActivity())
            finish()
        }

        binding.btnVencimentosProximosHomeLogada.setOnClickListener {
            trocarTela(VencimentosProximosActivity())
            finish()
        }


    }


}