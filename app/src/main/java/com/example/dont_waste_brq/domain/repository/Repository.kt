package com.example.dont_waste_brq.domain.repository

import com.example.dont_waste_brq.domain.model.Result
import com.example.dont_waste_brq.domain.model.Usuario

interface Repository {
    suspend fun cadastrarUsuario(usuario: Usuario): Result
}