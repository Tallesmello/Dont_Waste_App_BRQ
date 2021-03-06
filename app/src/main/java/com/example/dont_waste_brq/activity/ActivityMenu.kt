package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityMenuBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding

class ActivityMenu : BaseActivity() {

    private lateinit var binding : ActivityMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textCadastrar.setOnClickListener {
            trocarTela(LoginActivity())
        }


    }
}