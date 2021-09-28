package com.example.dont_waste_brq.viewmodel.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.domain.repository.Repository

class CadastroViewModelFactory(val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CadastroViewModel(repository) as T
}