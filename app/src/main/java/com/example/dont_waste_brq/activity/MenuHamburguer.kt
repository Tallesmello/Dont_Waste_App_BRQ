package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityMenuHamburguerBinding

class MenuHamburguer : BaseActivity() {

    lateinit var binding : ActivityMenuHamburguerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuHamburguerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textCadastrar.setOnClickListener {
            trocarTela(TelaCategorias())
        }

        binding.imageSair.setOnClickListener {
            finish()
        }
    }
}