package com.example.dont_waste_brq.util

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun AppCompatActivity.nextScreen(activity: AppCompatActivity){
    Intent(this, activity::class.java).apply {
        startActivity(this)
    }
}
fun AppCompatActivity.toast(mensagem : String){
    Toast.makeText(this,mensagem,Toast.LENGTH_LONG).show()
}
fun AppCompatActivity.dialog(titulo : String, mensagem: String){
    AlertDialog.Builder(this)
        .setTitle(titulo)
        .setMessage(mensagem)
        .setPositiveButton("ok"){dialogInterface,i->
            finish()
        }
        .create()
        .show()
}
fun View.show(){
    visibility  = View.VISIBLE
}
fun View.hide(){
    visibility  = View.GONE
}
fun AppCompatActivity.hideAll(vararg views: View){
    views.map {view->
        view.hide()
    }
}
fun AppCompatActivity.showAll(vararg views: View){
    views.map {view->
        view.hide()
    }
}

fun AppCompatActivity.snackbar(view: View,texto : String,time : Int = Snackbar.LENGTH_LONG){
    Snackbar.make(view,texto,time).show()
}



fun AppCompatActivity.nextScreen(activity: AppCompatActivity,acao : Pair<Int,Int>){
    Intent(this, activity::class.java).apply {
        flags = acao.first or acao.second
        startActivity(this)
    }
}