package com.example.dont_waste_brq.activity

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.data.FirebaseAuth
import com.example.dont_waste_brq.databinding.ActivityPrimeiroAcessoBinding
import com.example.dont_waste_brq.model.Usuario

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
           notificacaoPersonalizada(this, getString(R.string.botao_ajuda_senha))
                    }
        binding.btnProximoCadastro.setOnClickListener {
            if (dadosValidos()) {
                val user = gerandoUsuario()
                FirebaseAuth.cadastrarUsuario(user, binding.textEmailCadastro) { trocarTela(SegundaTelaCadastroActivity()) }
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

    fun notificacaoPersonalizada(context: Context, msg: String) {
        val toast: Toast = Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        );
        toast.setGravity(0, -80, 380);
        toast.show()
    }

}