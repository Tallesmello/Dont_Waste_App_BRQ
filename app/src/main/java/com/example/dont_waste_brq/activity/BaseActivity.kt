package com.example.dont_waste_brq.activity

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ConteudoInterno
import com.example.dont_waste_brq.model.TesteInclusao
import com.google.firebase.database.DatabaseReference

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var armazenar: Armazenar
    private lateinit var conteudo: ConteudoInterno
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun trocarTela(activity: AppCompatActivity) {
        Intent(this, activity::class.java).apply {
            startActivity(this)
        }
    }

    fun mensagem(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    fun gerarConteudoInternoParaEnvio(conteudo: ConteudoInterno): ConteudoInterno {


        return conteudo
    }


}

