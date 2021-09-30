package com.example.dont_waste_brq.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.example.dont_waste_brq.viewmodel.SegundaTelaCadastroViewModel
import com.google.android.material.datepicker.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.google.android.material.datepicker.CompositeDateValidator

import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CalendarConstraints.DateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward

import com.google.android.material.datepicker.DateValidatorPointForward





class SegundaTelaCadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding

    private lateinit var viewModel: SegundaTelaCadastroViewModel

    @SuppressLint("SimpleDateFormat")
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
        val datePicker = MaterialDatePicker
            .Builder
            .datePicker()
            .setCalendarConstraints(limites())
            .setTitleText("Selecione a data")
            .build()

        datePicker.addOnPositiveButtonClickListener { data ->
            var dateString = ""
            try {
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                dateString = simpleDateFormat.format(data)
            } catch (ex: Exception) {
                Log.e("SegundaTelaCadastro",
                    "SimpleDateFormat exception \n${ex.message}")
            }
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

    private fun limites(): CalendarConstraints {
        val constraintsBuilderRange =  CalendarConstraints.Builder()

        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()

        calendarStart.add(Calendar.YEAR, -1)

        val minDate = calendarStart.timeInMillis
        val maxDate = calendarEnd.timeInMillis

        constraintsBuilderRange.setStart(minDate);
        constraintsBuilderRange.setEnd(maxDate);

        val dateValidatorMin: DateValidator = DateValidatorPointForward.from(minDate)
        val dateValidatorMax: DateValidator = DateValidatorPointBackward.before(maxDate)

        var  listValidators = ArrayList<DateValidator>()
        listValidators.add(dateValidatorMin)
        listValidators.add(dateValidatorMax)
        var  validators = CompositeDateValidator.allOf(listValidators)
        constraintsBuilderRange.setValidator(validators)

        return constraintsBuilderRange.build();
    }

}