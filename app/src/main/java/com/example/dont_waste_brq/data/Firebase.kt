package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.model.Usuario
import com.google.firebase.auth.FirebaseAuth

object Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun cadastrarUsuario(usuario: Usuario): Boolean {
        var result = false
        firebaseAuth.createUserWithEmailAndPassword(
            usuario.email,
            usuario.senha
        ).addOnCompleteListener { task ->
            result = task.isSuccessful
        }
        return result
    }

}

