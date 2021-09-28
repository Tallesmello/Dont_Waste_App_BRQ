package com.example.dont_waste_brq.data

import com.example.dont_waste_brq.domain.model.Result
import com.example.dont_waste_brq.domain.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

object Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun cadastrarUsuario(usuario: Usuario): Result {
        lateinit var result: Result
        firebaseAuth.createUserWithEmailAndPassword(
                usuario.email,
                usuario.senha
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result = Result(true)
                } else {
                    result = Result(errorMessage = task.exception?.message)
                }
        }.await()
        return result
    }

}