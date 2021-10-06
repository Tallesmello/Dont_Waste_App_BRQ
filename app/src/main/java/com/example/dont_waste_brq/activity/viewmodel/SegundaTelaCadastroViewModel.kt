package com.example.dont_waste_brq.activity.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.FrequenciaEnum
import com.example.dont_waste_brq.activity.enum.QtdPessoasEnum
import com.google.android.material.datepicker.*
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SegundaTelaCadastroViewModel : ViewModel() {

    fun spinnerFrequencia(context: Context,campoFrequencia : MaterialAutoCompleteTextView) {
        //genrencia o spinner da frequencia de compras
        val itemsFrequencia = listOf(
            FrequenciaEnum.SEMANALMENTE,
            FrequenciaEnum.QUINZENALMENTE,
            FrequenciaEnum.MENSALMENTE
        )
        val adapterFrequencia = ArrayAdapter(context, R.layout.list_item, itemsFrequencia)
        campoFrequencia.setAdapter(adapterFrequencia)
    }

    fun dataPicker(campoSpinner : MaterialAutoCompleteTextView ,supportFragment : FragmentManager) {
        val datePicker = construcaoDoCalendarView()
        datePicker.addOnPositiveButtonClickListener { data ->
            firmandoData(data,campoSpinner)
        }
        //metodo para o date picker aparecer corretamente
        campoSpinner.setOnFocusChangeListener { view, isFocused ->
            if (view.isInTouchMode && isFocused) {
                view.performClick()
            }
        }

        //mostrar o date picker ao clicar no edit text
        campoSpinner.setOnClickListener {
            datePicker.show(supportFragment, "tag")
        }
    }

    fun SpinnerQtdPessoas(context: Context,campo : MaterialAutoCompleteTextView) {
        //gerencia o spinner(lista de opção) com a quantidade de pessoas que residem na casa
        val itemsPessoas = listOf(
            QtdPessoasEnum.UM.descricao,
            QtdPessoasEnum.DOIS.descricao,
            QtdPessoasEnum.TRES.descricao,
            QtdPessoasEnum.TRES_OU_MAIS.descricao
        )
        val adapterPessoas = ArrayAdapter(context, R.layout.list_item, itemsPessoas)
        campo.setAdapter(adapterPessoas)
    }

    @SuppressLint("SimpleDateFormat")
    private fun firmandoData(data: Long?,campo : MaterialAutoCompleteTextView) {
        var dateString = ""
        try {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            dateString = simpleDateFormat.format(data)
        } catch (ex: Exception) {
            Log.e(
                "SegundaTelaCadastro",
                "SimpleDateFormat exception \n${ex.message}"
            )
        }
        campo.setText(dateString)
    }

    private fun construcaoDoCalendarView(): MaterialDatePicker<Long> {
        //o date picker, para conseguir puxar o calendario
        val datePicker = MaterialDatePicker
            .Builder
            .datePicker()
            .setCalendarConstraints(limites())
            .setTitleText("Selecione a data")
            .build()
        return datePicker
    }

    private fun limites(): CalendarConstraints {
        val constraintsBuilderRange = CalendarConstraints.Builder()

        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()

        calendarStart.add(Calendar.YEAR, -1)

        val minDate = calendarStart.timeInMillis
        val maxDate = calendarEnd.timeInMillis

        constraintsBuilderRange.setStart(minDate);
        constraintsBuilderRange.setEnd(maxDate);

        val dateValidatorMin: CalendarConstraints.DateValidator = DateValidatorPointForward.from(minDate)
        val dateValidatorMax: CalendarConstraints.DateValidator = DateValidatorPointBackward.before(maxDate)

        var listValidators = ArrayList<CalendarConstraints.DateValidator>()
        listValidators.add(dateValidatorMin)
        listValidators.add(dateValidatorMax)
        var validators = CompositeDateValidator.allOf(listValidators)
        constraintsBuilderRange.setValidator(validators)

        return constraintsBuilderRange.build();
    }



}