package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun mensagem(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    protected fun trocandoTelaPara(activity: AppCompatActivity) {
       Intent(applicationContext,activity::class.java).apply {
           startActivity(this)
       }
    }


}
