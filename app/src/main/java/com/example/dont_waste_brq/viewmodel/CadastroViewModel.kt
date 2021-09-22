package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class CadastroViewModel: ViewModel() {

    fun notificacao(msg: String, context: Context) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }
}