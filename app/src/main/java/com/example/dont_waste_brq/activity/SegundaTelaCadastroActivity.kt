package com.example.dont_waste_brq.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.example.dont_waste_brq.activity.viewmodel.SegundaTelaCadastroViewModel
import com.google.android.material.textfield.TextInputEditText

class SegundaTelaCadastroActivity : BaseActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding

    private val viewModel: SegundaTelaCadastroViewModel by lazy {
        ViewModelProvider(this).get(SegundaTelaCadastroViewModel::class.java)
    }
    private lateinit var nome : String
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initCampos()
        initListeners()

    }

    private fun initCampos() {
        nome = binding.editNomeSegundaTelaCadastro.text.toString()
        viewModel.SpinnerQtdPessoas(this,binding.materialAutoCompleteTextViewQuantidadePessoas)
        viewModel.dataPicker(binding.editFrequenciaComprasSegundaTelaCadastro,supportFragmentManager)
        viewModel.spinnerFrequencia(this,binding.editFrequenciaComprasSegundaTelaCadastro)
    }

    private fun initListeners() {
        binding.btnVoltaHmNLogadaSegundaTelaCadastro.setOnClickListener {
            trocarTela(HomeNaoLogadaActivity())
        }

        binding.btnVoltarSegundaTelaCadastro.setOnClickListener {
            trocarTela(PrimeiroAcessoActivity())
        }

        binding.btnSalvarSegundaTelaCadastro.setOnClickListener {
            trocarTela(LoginActivity())
        }
    }




}