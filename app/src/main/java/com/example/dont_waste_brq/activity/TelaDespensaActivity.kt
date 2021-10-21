package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityTelaCategoriasBinding
import com.example.dont_waste_brq.databinding.ActivityTelaDespensaBinding

class TelaDespensaActivity : BaseActivity() {

    private lateinit var binding: ActivityTelaDespensaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaDespensaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mensagem("Tela abriu")
    }
}