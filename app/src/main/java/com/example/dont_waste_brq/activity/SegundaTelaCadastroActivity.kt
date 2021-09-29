package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.example.dont_waste_brq.viewmodel.SegundaTelaCadastroViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat


class SegundaTelaCadastroActivity : BaseActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding
    private lateinit var viewModel: SegundaTelaCadastroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SegundaTelaCadastroViewModel::class.java)
        val spinnerQtdPessoas = binding.materialAutoCompleteTextViewQuantidadePessoas
        val dataPicker = binding.editDataCompraSegundaTelaCadastro
        val frquenciaDeCompras = binding.editFrequenciaComprasSegundaTelaCadastro
        val nome = binding.editNomeSegundaTelaCadastro

        //        [12:53] Gustavo Wellington Reis Xavier Torres
        //gerencia o spinner(lista de opção) com a quantidade de pessoas que residem na casa
        viewModel.quantidadePessoasSpinner(
            this,
            spinnerQtdPessoas
        )
        //o date picker, para conseguir puxar o calendario
        val datePicker = viewModel.criacaoDataPicker()

        //quando clica em ok ao escolher uma data no date picker
        viewModel.salvandoDataNoDataPicker(datePicker, dataPicker)

        //metodo para o date picker aparecer corretamente
        dataPicker.setOnFocusChangeListener { view, isFocused ->
            viewModel.recuperandoFocoDataPicker(view, isFocused)
        }

        //mostrar o date picker ao clicar no edit text
        dataPicker.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }

        //genrencia o spinner da frequencia de compras
        viewModel.opcoesFrequenciaSpinner(this, frquenciaDeCompras)
        binding.btnVoltaHmNLogadaSegundaTelaCadastro.setOnClickListener {
            startActivity(viewModel.trocandoTelaPara(this, HomeNaoLogadaActivity()))
        }

        binding.btnVoltarSegundaTelaCadastro.setOnClickListener {
            startActivity(viewModel.trocandoTelaPara(this, CadastroActivity()))
        }

        binding.btnSalvarSegundaTelaCadastro.setOnClickListener {
            if (viewModel.validacaoSpinnerFrequencia(frquenciaDeCompras,this) == 1
                && viewModel.validacaoDataPicker(dataPicker,this) == 1
                && viewModel.validacaoQuantidadePessoas(spinnerQtdPessoas,this) == 1
                && viewModel.validacaoNome(nome,this) == 1
            ) {
                startActivity(viewModel.trocandoTelaPara(this, LoginActivity()))
            }
        }

    }



}