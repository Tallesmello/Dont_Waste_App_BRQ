package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityDespensaBinding
import com.example.dont_waste_brq.databinding.ActivityTelaCategoriasBinding

class TelaDespensaActivity : BaseActivity() {


    private lateinit var binding: ActivityDespensaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDespensaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnVoltarDespensa.setOnClickListener {
            trocarTela(TelaCategoriasActivity())
            finish()
        }
    }
}