package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun mensagemToast(context: Context, msg: String) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }
}