package com.example.dont_waste_brq.activity
import com.example.dont_waste_brq.activity.BaseActivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.widget.ToolbarWidgetWrapper
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.PrimeiroAcessoActivity
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.databinding.ActivityRelatorioDeDesperdicioBinding
import com.example.dont_waste_brq.activity.VencimentosProximosActivity as VencimentosProximosActivity

class DesperdiciosActivity : BaseActivity() {

    private lateinit var binding : ActivityRelatorioDeDesperdicioBinding
    private lateinit var appBar : ToolbarWidgetWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioDeDesperdicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      val toolbar = binding.toolbarDesperdicios.toolbar
        setSupportActionBar(toolbar)




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.icon_appbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.action_settings.apply {
            trocarTela(ActivityMenu())
        }
        return super.onOptionsItemSelected(item)
    }



}