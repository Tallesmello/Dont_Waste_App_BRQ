package com.example.dont_waste_brq.viewmodel.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dont_waste_brq.domain.model.Result
import com.example.dont_waste_brq.domain.model.Usuario
import com.example.dont_waste_brq.domain.repository.Repository
import kotlinx.coroutines.launch

class CadastroViewModel(val repository: Repository): ViewModel() {

    private var _cadastroState: MutableLiveData<CadastroState> = MutableLiveData()
    val cadastroState: LiveData<CadastroState> = _cadastroState

    fun cadastrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            val result = repository.cadastrarUsuario(usuario)
            finishCadastrarUsuario(result)
        }
    }

    private fun finishCadastrarUsuario(result: Result) {
        if (result.success) {
            _cadastroState.value = CadastroState.Sucesso
        } else {
            _cadastroState.value = CadastroState.Erro(result.errorMessage)
        }
    }

}