package com.example.dont_waste_brq.activity

import android.os.Bundle
import com.example.dont_waste_brq.databinding.ActivityTelaDespensaBinding

class TelaDespensaActivity : BaseActivity() {

    private lateinit var binding: ActivityTelaDespensaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaDespensaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * botão de voltar para tela categorias
         */
                binding.btnVoltarDespensa.setOnClickListener {
                    trocarTela(TelaCategoriasActivity())
                    mensagem("Tela abriu")
                    finish()
                }

            }
        }

