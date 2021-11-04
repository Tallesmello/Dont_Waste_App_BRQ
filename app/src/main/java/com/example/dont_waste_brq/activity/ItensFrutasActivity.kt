package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding

class ItensFrutasActivity : BaseActivity() {

    private lateinit var binding: ActivityItensFrutasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voltarTelaGeladeiras()
        alimentosCadastrados()


        binding.buttonNovoItem.setOnClickListener {
            if (binding.layoutNovoItem.visibility == View.GONE) {
                binding.buttonNovoItem.visibility = View.GONE
                binding.layoutNovoItem.visibility = View.VISIBLE
            } else {
                binding.layoutNovoItem.visibility = View.GONE
            }
        }

    }

    private fun voltarTelaGeladeiras(){
        binding.btnVoltarItemFrutas.setOnClickListener {
            trocarTela(TelaGaladeiraActivity())
        }
    }

    private fun alimentosCadastrados(){
        binding.btnAlimentosCadastradosItemFrutas.setOnClickListener {
            trocarTela(AlimentosCadastradosActivity())
        }
    }
}