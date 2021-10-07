package com.example.dont_waste_brq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dont_waste_brq.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun trocarTela(activity: AppCompatActivity){
        Intent(this,activity::class.java).apply {
            startActivity(this)
        }
    }
    fun mensagem(msg : String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

}

