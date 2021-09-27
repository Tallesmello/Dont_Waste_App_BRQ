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
import java.text.SimpleDateFormat


class SegundaTelaCadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding
    private var tudoOk: Boolean = true
    private lateinit var viewModel: SegundaTelaCadastroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SegundaTelaCadastroViewModel::class.java)

//        [12:53] Gustavo Wellington Reis Xavier Torres
//gerencia o spinner(lista de opção) com a quantidade de pessoas que residem na casa

        viewModel.quantidadePessoasSpinner(
            this,
            binding.materialAutoCompleteTextViewQuantidadePessoas
        )
//o date picker, para conseguir puxar o calendario

        val datePicker = viewModel.criacaoDataPicker()
//quando clica em ok ao escolher uma data no date picker

        viewModel.salvandoDataNoDataPicker(datePicker, binding.editDataCompraSegundaTelaCadastro)
//metodo para o date picker aparecer corretamente

        binding.editDataCompraSegundaTelaCadastro.setOnFocusChangeListener { view, isFocused ->
            viewModel.recuperandoFocoDataPicker(view, isFocused)
        }
//mostrar o date picker ao clicar no edit text

        binding.editDataCompraSegundaTelaCadastro.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }
//genrencia o spinner da frequencia de compras

        viewModel.opcoesFrequenciaSpinner(this, binding.editFrequenciaComprasSegundaTelaCadastro)


        binding.btnVoltaHmNLogadaSegundaTelaCadastro.setOnClickListener {
            startActivity(viewModel.trocandoTelaPara(this, HomeNaoLogadaActivity()))
        }

        binding.btnVoltarSegundaTelaCadastro.setOnClickListener {
            startActivity(viewModel.trocandoTelaPara(this, CadastroActivity()))

        }

        binding.btnSalvarSegundaTelaCadastro.setOnClickListener {
            if (validacaoSpinnerFrequencia() == 1
                && validacaoDataPicker() == 1
                && validacaoQuantidadePessoas() == 1
                && validacaoNome() == 1
            ) {
                startActivity(viewModel.trocandoTelaPara(this, LoginActivity()))
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun validacaoSpinnerFrequencia(): Int {
        var dataFrequencia = binding.editFrequenciaComprasSegundaTelaCadastro.text.toString()
        if (dataFrequencia.isNullOrEmpty()) {
            Toast.makeText(this, "Insira todos os campos 1", Toast.LENGTH_LONG).show()
        } else {
            return 1
        }
        return 0
    }

    fun validacaoDataPicker(): Int {
        var ultimaCompra = binding.editDataCompraSegundaTelaCadastro.text.toString()
        if (ultimaCompra.isNullOrEmpty()) {
            Toast.makeText(this, "Insira todos os campos 3", Toast.LENGTH_LONG).show()
        } else {
            return 1
        }
        return 0
    }

    fun validacaoQuantidadePessoas(): Int {
        var quantidadePessoas =
            binding.materialAutoCompleteTextViewQuantidadePessoas.text.toString()
        if (quantidadePessoas.isNullOrEmpty()) {
            Toast.makeText(this, "Insira todos os campos 2", Toast.LENGTH_LONG).show()
        } else {
            return 1
        }
        return 0
    }

    fun validacaoNome(): Int {
        var nome = binding.editNomeSegundaTelaCadastro.text.toString()
        if (nome.isNullOrEmpty()) {
            Toast.makeText(this, "Insira todos os campos 4", Toast.LENGTH_LONG).show()

        } else {
            return 1
        }
        return 0
    }
}