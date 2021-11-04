package com.example.dont_waste_brq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dont_waste_brq.data.ItemDAO
import com.example.dont_waste_brq.databinding.ActivityItensFrutasBinding

class ItensFrutasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItensFrutasBinding
    private val dao = ItemDAO()
    private val adapter = AdapterItensFrutas(dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItensFrutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configuraRecyclerView()

        binding.buttonNovoItem.setOnClickListener {
            if (binding.layoutNovoItem.visibility == View.GONE) {
                binding.buttonNovoItem.visibility = View.GONE
                binding.layoutNovoItem.visibility = View.VISIBLE
            } else {
                binding.layoutNovoItem.visibility = View.GONE
            }
        }

    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.recyclerItens
        recyclerView.adapter = adapter
    }
}