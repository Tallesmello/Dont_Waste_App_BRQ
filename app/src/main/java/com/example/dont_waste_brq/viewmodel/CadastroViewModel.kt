package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

class CadastroViewModel: ViewModel() {

    fun notificacao(msg: String, context: Context) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }

   fun notificacaoPersonalizada(context: Context, msg: String) {
        val toast: Toast = Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        );
        toast.setGravity(0, -80, 270);
        toast.show()
    }

    fun trocandoTelaPara(context: Context, activity: AppCompatActivity): Intent {
        return Intent(context, activity::class.java)
    }
}