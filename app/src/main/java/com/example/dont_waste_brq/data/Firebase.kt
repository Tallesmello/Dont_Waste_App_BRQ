package com.example.dont_waste_brq.data

import android.content.Context
import android.provider.Settings.Global.getString
import android.widget.Toast
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.model.Usuario
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

object Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()


    fun cadastrarUsuario(usuario: Usuario, campoEmail: TextInputLayout, sucesso: () -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(

            usuario.email,
            usuario.senha
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                sucesso()
            } else if (!task.isSuccessful()) {
                try {
                    throw task.getException()!!
                } catch (e: FirebaseAuthUserCollisionException) {
                    campoEmail.error = "Email já cadastrado"
                    campoEmail.requestFocus()

                }

            }
        }
    }
        fun resetSenha(email: String, context: Context, campoTexto: TextInputLayout): Boolean {
            var result = false
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener { task ->
                    result = true
                }
                .addOnFailureListener {
                    campoTexto.error = "Email Inválido"
                }
            return result
        }

    }





