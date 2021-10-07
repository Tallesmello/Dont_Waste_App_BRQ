package com.example.dont_waste_brq.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.FrequenciaEnum
import com.example.dont_waste_brq.activity.enum.QtdPessoasEnum
import com.example.dont_waste_brq.databinding.ActivitySegundaTelaCadastroBinding
import com.example.dont_waste_brq.model.Cadastro
import com.google.android.material.datepicker.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SegundaTelaCadastroActivity : BaseActivity() {

    private lateinit var binding: ActivitySegundaTelaCadastroBinding

    private var dataValida = Date()
    private val datePicker: MaterialDatePicker<Long> by lazy { setupDatePicker() }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupAutocomplete()
        setupListners()
    }

    private fun setupAutocomplete() {
        val adapterPessoas =
            ArrayAdapter(this, R.layout.list_item, QtdPessoasEnum.getArrayListOfDescricao())
        binding.materialAutoCompleteTextViewQuantidadePessoas.setAdapter(adapterPessoas)


        // todo - o listOf<String>() deve ser corrigido
        val adapterFrequencia = ArrayAdapter(this, R.layout.list_item, FrequenciaEnum.getArrayListOfFrequencia())
        binding.editFrequenciaComprasSegundaTelaCadastro.setAdapter(adapterFrequencia)
    }

    private fun setupListners() {
        binding.btnVoltaHmNLogadaSegundaTelaCadastro.setOnClickListener {
           trocarTela(HomeNaoLogadaActivity())
        }
        binding.btnVoltarSegundaTelaCadastro.setOnClickListener {
            trocarTela(PrimeiroAcessoActivity())
        }
        binding.btnSalvarSegundaTelaCadastro.setOnClickListener {
            if (validarCadastro()) {
                salvarCadastro()
                trocarTela(HomeLogadaActivity())
            }
        }
        /* será que isso é necessário ???
        binding.editDataCompraSegundaTelaCadastro.setOnFocusChangeListener { view, isFocused ->
            if (view.isInTouchMode && isFocused) {
                view.performClick()
            }
        } */
        binding.editDataCompraSegundaTelaCadastro.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }
    }

    private fun validarCadastro(): Boolean {
        var res = true
        binding.textNomeSegundaTelaCadastro.error = null
        binding.textQuantidadePessoasSegundaTelaCadastro.error = null
        if (binding.editNomeSegundaTelaCadastro.text.toString().trim().length < 4) {
            binding.textNomeSegundaTelaCadastro.error = getString(R.string.nome_invalido)
            binding.textNomeSegundaTelaCadastro.isErrorEnabled = true
            res = false
        } else {
            binding.textNomeSegundaTelaCadastro.isErrorEnabled = false
        }


        if (QtdPessoasEnum.getEnumFromDescricao(
                binding
                    .materialAutoCompleteTextViewQuantidadePessoas.text.toString()
            ) == null
        ) {
            binding.textQuantidadePessoasSegundaTelaCadastro.isErrorEnabled = true
            binding.textQuantidadePessoasSegundaTelaCadastro.error =
                getString(R.string.quantidade_nao_selecionada)
            res = false
        } else {
            binding.textQuantidadePessoasSegundaTelaCadastro.isErrorEnabled = false
        }


        if (binding.editDataCompraSegundaTelaCadastro.text.isNullOrEmpty()) {
            binding.textDataCompraSegundaTelaCadastro.error = getString(R.string.data_invalida)
            binding.textDataCompraSegundaTelaCadastro.isErrorEnabled = true
            res = false
        } else {
            binding.textDataCompraSegundaTelaCadastro.isErrorEnabled = false
        }


        if (FrequenciaEnum.getEnumFromFrequencia(
                binding
                    .editFrequenciaComprasSegundaTelaCadastro.text.toString()
            ) == null
        ) {
            binding.textFrequenciaComprasSegundaTelaCadastro.isErrorEnabled = true
            binding.textFrequenciaComprasSegundaTelaCadastro.error =
                getString(R.string.quantidade_nao_selecionada)
            res = false
        } else {
            binding.textFrequenciaComprasSegundaTelaCadastro.isErrorEnabled = false
        }


        return res
    }


    private fun salvarCadastro() {
        val cadastro = Cadastro(
            binding.editNomeSegundaTelaCadastro.text.toString(),
            QtdPessoasEnum.getEnumFromDescricao(
                binding.materialAutoCompleteTextViewQuantidadePessoas.text.toString()
            ),
            dataValida,
            FrequenciaEnum.getEnumFromFrequencia(
                binding.editFrequenciaComprasSegundaTelaCadastro.text.toString()

            )
        )
//        Firebase.cadastrarUsuario()
////        if (cadastro salvo com sucesso) {
//        trocarTela(LoginActivity())
}



private fun setupDatePicker(): MaterialDatePicker<Long> {
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
            dataValida = Date(data)
        } catch (ex: Exception) {
            Log.e(
                "SegundaTelaCadastro",
                "SimpleDateFormat exception \n${ex.message}"
            )
        }
        binding.editDataCompraSegundaTelaCadastro.setText(dateString)
    }
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
