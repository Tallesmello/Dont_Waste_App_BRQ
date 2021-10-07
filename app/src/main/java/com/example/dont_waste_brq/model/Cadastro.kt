package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.FrequenciaEnum
import com.example.dont_waste_brq.activity.enum.QtdPessoasEnum
import java.util.*

data class Cadastro(
    val nomeCadastro: String,
    val qtdPessoas: QtdPessoasEnum?,
    val ultimaData: Date,
    val fraquenciaCompra: FrequenciaEnum?
){

}
