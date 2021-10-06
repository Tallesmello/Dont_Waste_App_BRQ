package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.model.Usuario
import com.google.firebase.auth.FirebaseAuth

object Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun cadastrarUsuario(usuario: Usuario, sucesso: () -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(
            usuario.email,
            usuario.senha
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                sucesso()
            }
        }
    }

    fun resetSenha(usuario: Usuario): Boolean {
        var result = false
        firebaseAuth.sendPasswordResetEmail(usuario.email)
            .addOnCompleteListener { task ->
                result = task.isSuccessful
            }
        return result
    }

}

