package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R

class TelaDespensaActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_despensa)

        mensagem("Tela abriu")
    }
}