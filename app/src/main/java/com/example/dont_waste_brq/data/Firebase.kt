package com.example.dont_waste_brq.data

import android.content.Context
import android.widget.Toast
import com.example.dont_waste_brq.model.Usuario
import com.google.android.material.textfield.TextInputLayout
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

    fun resetSenha(email : String, context: Context, campoTexto: TextInputLayout): Boolean {
        var result = false
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener { task ->
                    Toast.makeText(context, "Recuperação de senha encaminhado para o email cadastrado", Toast.LENGTH_LONG).show()
                result = true
            }
                .addOnFailureListener{
                    campoTexto.error = "Email Inválido"
                }
        return result
            }

    }



