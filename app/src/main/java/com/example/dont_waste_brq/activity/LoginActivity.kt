package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityLoginBinding
import com.example.dont_waste_brq.data.Firebase
import com.example.dont_waste_brq.model.Usuario

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()

    }

    private fun initListeners() {
        binding.btnLoginTelaLogin.setOnClickListener {
            if (dadosValidos()) {
                val userExistente = Usuario(
                    binding.editEmailLogin.text.toString(),
                    binding.editSenhaLogin.text.toString()
                )
                // Usar o Login salvo no Firebase
                autenticacaoEmailESenhaFirebase(userExistente)
            }
        }
        binding.btnEsqueciSenhaLogin.setOnClickListener {
            trocarTela(EsqueciMinhaSenhaActivity())
        }

        binding.btnVoltaHmNLogadaLogin.setOnClickListener {
            trocarTela(HomeNaoLogadaActivity())
            finish()
        }
    }

    private fun autenticacaoEmailESenhaFirebase(usuario: Usuario){
        Firebase.cadastrarUsuario(usuario, binding.textEmailLogin, {sucesso()}).let {
            Intent(this, HomeLogadaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }
    }

    private fun sucesso() {
        mensagem("Deu certo")
    }

    private fun dadosValidos(): Boolean {
        var ok = true
        val email = binding.editEmailLogin.text.toString()
        val senha = binding.editSenhaLogin.text.toString()
        binding.textEmailLogin.error = ""
        binding.textSenhaLogin.error = ""
        if (email.trim().isEmpty() ||
            !Patterns.EMAIL_ADDRESS
                .matcher(email).matches()
        ) {
            binding.textEmailLogin.error = getString(R.string.dados_invalidos)
            ok = false
        }
        if (senha.trim().length < 6) {
            binding.textSenhaLogin.error = getString(R.string.dados_invalidos)
            ok = false
        }
        return ok
    }



}





