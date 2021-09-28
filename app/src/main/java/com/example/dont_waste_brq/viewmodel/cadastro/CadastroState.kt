package com.example.dont_waste_brq.viewmodel.cadastro

sealed class CadastroState {
    object Sucesso : CadastroState()
    data class Erro(val mensagem: String?) : CadastroState()
}
