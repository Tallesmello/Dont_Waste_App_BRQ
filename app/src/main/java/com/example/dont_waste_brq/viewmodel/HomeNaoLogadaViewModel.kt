package com.example.dont_waste_brq.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dont_waste_brq.R

class HomeNaoLogadaViewModel: ViewModel() {

    fun trocandoTelaPara(context: Context, activity: AppCompatActivity): Intent {
        return Intent(context, activity::class.java)
    }
}