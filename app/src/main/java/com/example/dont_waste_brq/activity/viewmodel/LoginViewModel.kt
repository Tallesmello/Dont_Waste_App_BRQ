package com.example.dont_waste_brq.activity.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun mensagemToast(context: Context, msg: String) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }
    fun validandoEmailSenhaLogin(campoEmail : String , campoSenha : String): Pair<String, String> {
        val email: String = campoEmail.trim { it <= ' ' }
        val senha: String = campoSenha.trim { it <= ' ' }
        return Pair(email, senha)
    }


    fun trocandoTelaPara(context: Context, activity: AppCompatActivity): Intent {
        return Intent(context, activity::class.java)
    }
}