package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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


    fun validacaoSpinnerFrequencia(campo : MaterialAutoCompleteTextView, context: Context): Int {
        var dataFrequencia = campo.text.toString()
        if (dataFrequencia.isNullOrEmpty()) {
            Toast.makeText(context, "Preencha a frequência de compras", Toast.LENGTH_LONG).show()
        } else {
            return 1
        }
        return 0
    }

    fun validacaoDataPicker(campo : TextInputEditText,context: Context): Int {
        var ultimaCompra = campo.text.toString()
        if (ultimaCompra.isNullOrEmpty()) {
            mensagem(context,"Selecione a data da ultima compra")
        } else {
            return 1
        }
        return 0
    }



    fun validacaoQuantidadePessoas(campo : MaterialAutoCompleteTextView, context: Context): Int {
        var quantidadePessoas =
            campo.text.toString()
        if (quantidadePessoas.isNullOrEmpty()) {
            mensagem(context,"Selecione a quantidade de pessoas")
        } else {
            return 1
        }
        return 0
    }

    fun validacaoNome(campo : TextInputEditText,context: Context): Int {
        var nome = campo.text.toString()
        if (nome.isNullOrEmpty()) {
            mensagem(context,"Informe um nome")

        } else {
            return 1
        }
        return 0
    }
    private fun mensagem(context: Context,msg : String) {
        Toast.makeText(context,msg , Toast.LENGTH_LONG).show()
    }

}