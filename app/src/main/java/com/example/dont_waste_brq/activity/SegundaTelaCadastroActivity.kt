package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.example.dont_waste_brq.viewmodel.SegundaTelaCadastroViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat

class SegundaTelaCadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding

    private lateinit var viewModel: SegundaTelaCadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        [12:53] Gustavo Wellington Reis Xavier Torres
//gerencia o spinner(lista de opção) com a quantidade de pessoas que residem na casa
        val itemsPessoas = listOf("1", "2", "3", "Mais de 3")
        val adapterPessoas = ArrayAdapter(this, R.layout.list_item, itemsPessoas)
        binding.materialAutoCompleteTextViewQuantidadePessoas.setAdapter(adapterPessoas)


//o date picker, para conseguir puxar o calendario
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Selecione a data").build()


//quando clica em ok ao escolher uma data no date picker
        datePicker.addOnPositiveButtonClickListener {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            val dateString = simpleDateFormat.format(it)
            binding.editDataCompraSegundaTelaCadastro.setText(dateString)
        }
//metodo para o date picker aparecer corretamente
        binding.editDataCompraSegundaTelaCadastro.setOnFocusChangeListener { view, isFocused ->
            if (view.isInTouchMode && isFocused) {
                view.performClick()
            }

        }
//mostrar o date picker ao clicar no edit text
        binding.editDataCompraSegundaTelaCadastro.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }
//genrencia o spinner da frequencia de compras
        val itemsFrequencia = listOf("Semanal", "Quinzenal", "Mensal")
        val adapterFrequencia = ArrayAdapter(this, R.layout.list_item, itemsFrequencia)
        binding.editFrequenciaComprasSegundaTelaCadastro.setAdapter(adapterFrequencia)



        viewModel = ViewModelProvider(this).get(SegundaTelaCadastroViewModel::class.java)

        binding.btnVoltaHmNLogadaSegundaTelaCadastro.setOnClickListener {
            startActivity(Intent(this, HomeNaoLogadaActivity::class.java))
        }

        binding.btnVoltarSegundaTelaCadastro.setOnClickListener {
            val intentVoltar = Intent(this, CadastroActivity::class.java)
            startActivity(intentVoltar)
        }

        binding.btnSalvarSegundaTelaCadastro.setOnClickListener {
            val intentSalvar = Intent(this, LoginActivity::class.java)
            startActivity(intentSalvar)
        }

    }

}