package com.example.dont_waste_brq.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.viewmodel.SegundaTelaCadastroViewModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_segunda_tela_cadastro.*
import java.text.SimpleDateFormat
import java.util.*

class SegundaTelaCadastroActivity : AppCompatActivity(){

    private lateinit var viewModel: SegundaTelaCadastroViewModel
    private lateinit var dataUltimaCompra1: TextInputEditText

    lateinit var btnVoltarHomeSegundaTelaCadastro: AppCompatImageView
    lateinit var btnVoltarCadastro: AppCompatButton
    lateinit var btnSalvarCadastro: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_tela_cadastro)


        btnVoltarHomeSegundaTelaCadastro =
            findViewById(R.id.btn_volta_hm_n_logada_segunda_tela_cadastro)
        btnVoltarCadastro = findViewById(R.id.btn_voltar_segunda_tela_cadastro)
        btnSalvarCadastro = findViewById(R.id.btn_salvar_segunda_tela_cadastro)
        dataUltimaCompra1 = findViewById(R.id.edit_data_compra_segunda_tela_cadastro)

        viewModel = ViewModelProvider(this).get(SegundaTelaCadastroViewModel::class.java)

        btnVoltarHomeSegundaTelaCadastro.setOnClickListener {
            startActivity(Intent(this, HomeNaoLogadaActivity::class.java))
        }

        btnVoltarCadastro.setOnClickListener {
            val intentVoltar = Intent(this, CadastroActivity::class.java)
            startActivity(intentVoltar)
        }

        btnSalvarCadastro.setOnClickListener {
            val intentSalvar = Intent(this, LoginActivity::class.java)
            startActivity(intentSalvar)
        }

    }

}