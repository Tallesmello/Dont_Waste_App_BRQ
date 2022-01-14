package com.example.dont_waste_brq.util.helper

import com.example.dont_waste_brq.activity.enum.FrequenciaEnum
import java.util.regex.Pattern

object ValidarCampoHelper {

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun   emailValido(email: String): Boolean {
        return email.trim().isNotEmpty() &&
                EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun senhaValida(senha: String, tamanhoMinimo: Int = 6): Boolean {
        return (senha.trim().length in tamanhoMinimo..8 && senha != null)
    }

    fun frequenciaCompraValida(frequencia: String): Boolean {
        return FrequenciaEnum.getEnumFromFrequencia(frequencia) != null
    }
}