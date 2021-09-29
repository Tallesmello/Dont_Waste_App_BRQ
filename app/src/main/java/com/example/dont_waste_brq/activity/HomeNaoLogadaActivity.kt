package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityHomeNaoLogadaBinding
import com.example.dont_waste_brq.viewmodel.HomeNaoLogadaViewModel


class HomeNaoLogadaActivity : BaseActivity() {

    private lateinit var viewModel : HomeNaoLogadaViewModel
    private lateinit var binding : ActivityHomeNaoLogadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeNaoLogadaBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(HomeNaoLogadaViewModel::class.java)
        val view = binding.root
        setContentView(view)

        binding.btnPrimeiroAcessoHmNLogada.setOnClickListener {
            viewModel.trocandoTelaPara(this,CadastroActivity())
        }

        binding.btnLoginHmNLogada.setOnClickListener {
            viewModel.trocandoTelaPara(this,LoginActivity())
        }
    }

}