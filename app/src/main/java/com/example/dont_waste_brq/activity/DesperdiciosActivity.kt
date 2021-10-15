package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toolbar
import androidx.appcompat.widget.ToolbarWidgetWrapper
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding

class DesperdiciosActivity : BaseActivity() {

    private lateinit var binding : ActivityRelatorioDeDesperdicioBinding
    private lateinit var appBar : ToolbarWidgetWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioDeDesperdicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       appBar = findViewById
        setSupportActionBar(appBar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.icon_appbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

}