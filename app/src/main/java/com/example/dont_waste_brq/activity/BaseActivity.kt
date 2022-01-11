package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO
import com.example.dont_waste_brq.util.dialog

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var armazenar: Armazenar
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




}

