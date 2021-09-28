package com.example.dont_waste_brq.domain.repository

import com.example.dont_waste_brq.data.Firebase
import com.example.dont_waste_brq.domain.model.Usuario

class RepositoryImpl : Repository{
    override suspend fun cadastrarUsuario(usuario: Usuario) = Firebase.cadastrarUsuario(usuario)
}