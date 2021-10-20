package com.example.dont_waste_brq.data

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
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

    fun logarUsuario(usuario: Usuario, sucesso: (task: Task<AuthResult>) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(
            usuario.email,
            usuario.senha
        ).addOnCompleteListener { task ->
            sucesso(task)
        }
    }

        fun resetSenha(email: String, sucesso: (task: Task<Void>) -> Unit) {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    sucesso(it)
                }
        }

    }





