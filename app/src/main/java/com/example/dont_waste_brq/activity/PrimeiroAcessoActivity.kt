package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.data.Firebase
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.model.Usuario
import com.google.firebase.auth.FirebaseAuth

class PrimeiroAcessoActivity : BaseActivity() {

    private lateinit var binding: ActivityPrimeiroAcessoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrimeiroAcessoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()
    }

    private fun initListeners() {
        binding.btnVoltaCadastro.setOnClickListener {
            trocarTela(HomeNaoLogadaActivity())
            finish()
        }
        binding.btnAjudaCadastro.setOnClickListener {
            mensagem("\"A senha deve conter de 6 a 8 caracteres numéricos\"")
        }
        binding.btnProximoCadastro.setOnClickListener {
            if (dadosValidos()) {
                val user = gerandoUsuario()
                Firebase.cadastrarUsuario(user) { trocarTela(SegundaTelaCadastroActivity()) }
            }
        }
    }

    private fun gerandoUsuario(): Usuario {
        val user = Usuario(
            binding.editEmailCadastro.text.toString(),
            binding.editSenhaCadastro.text.toString()
        )
        return user
    }

    private fun dadosValidos(): Boolean {
        var ok = true
        val email = binding.editEmailCadastro.text.toString()
        val senha = binding.editSenhaCadastro.text.toString()
        binding.textEmailCadastro.error = ""
        binding.textSenhaCadastro.error = ""
        if (email.trim().isEmpty() ||
            !Patterns.EMAIL_ADDRESS
                .matcher(email).matches()
        ) {
            binding.textEmailCadastro.error = getString(R.string.insira_um_email_valido)
            ok = false
        }
        if (senha.trim().length < 6) {
            binding.textSenhaCadastro.error = getString(R.string.insira_uma_senha_valido)
            ok = false
        }
        return ok
    }


}