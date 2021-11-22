package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.adapter.ProdutoAdapter
import com.example.dont_waste_brq.databinding.ActivityAlimentosCadastradosBinding
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding

class AlimentosCadastradosActivity : BaseActivity() {

    private lateinit var binding: ActivityAlimentosCadastradosBinding
    private lateinit var adapter: ProdutoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlimentosCadastradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}