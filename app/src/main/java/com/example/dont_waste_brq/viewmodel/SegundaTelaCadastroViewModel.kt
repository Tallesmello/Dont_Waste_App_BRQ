package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dont_waste_brq.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat

class SegundaTelaCadastroViewModel : ViewModel() {

    private val mNome = MutableLiveData<String>()
    val nome: LiveData<String> = mNome

    fun trocandoTelaPara(context: Context, activity: AppCompatActivity): Intent {
        return Intent(context, activity::class.java)
    }

    fun opcoesFrequenciaSpinner(context: Context, campoSpinner: MaterialAutoCompleteTextView) {
        val itemsFrequencia = listOf("Semanal", "Quinzenal", "Mensal")
        val adapterFrequencia = ArrayAdapter(context, R.layout.list_item, itemsFrequencia)
        campoSpinner.setAdapter(adapterFrequencia)
    }

     fun salvandoDataNoDataPicker(datePicker: MaterialDatePicker<Long>, campoSpinnerDataCompra: TextInputEditText) {
        datePicker.addOnPositiveButtonClickListener {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            val dateString = simpleDateFormat.format(it)
            campoSpinnerDataCompra.setText(dateString)
        }
    }

    fun recuperandoFocoDataPicker(view: View, isFocused: Boolean) {
        if (view.isInTouchMode && isFocused) {
            view.performClick()
        }
    }

    fun quantidadePessoasSpinner(context: Context, spinnerQuantidadeDePessoas: MaterialAutoCompleteTextView) {
        val itemsPessoas  = listOf("1", "2", "3", "Mais de 3")
        val adapterPessoas = ArrayAdapter(context, R.layout.list_item, itemsPessoas)
        spinnerQuantidadeDePessoas.setAdapter(adapterPessoas)
    }

    fun criacaoDataPicker(): MaterialDatePicker<Long> {
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Selecione a data").build()
        return datePicker
    }

    fun validaCampos(dP: MaterialDatePicker<Long>,
                     spinnerFrequencia: MaterialAutoCompleteTextView,
                     spinnerQuantidadeDePessoas: MaterialAutoCompleteTextView,) {



    }





}