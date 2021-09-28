package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityCadastroBinding
import com.example.dont_waste_brq.domain.model.Usuario
import com.example.dont_waste_brq.domain.repository.RepositoryImpl
import com.example.dont_waste_brq.viewmodel.cadastro.CadastroState
import com.example.dont_waste_brq.viewmodel.cadastro.CadastroViewModel
import com.example.dont_waste_brq.viewmodel.cadastro.CadastroViewModelFactory

class CadastroActivity : BaseActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val viewModel: CadastroViewModel by lazy {
        ViewModelProvider(this, CadastroViewModelFactory(RepositoryImpl()))
            .get(CadastroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListners()
        setupObservers()
    }

    private fun setupListners() {
        binding.btnVoltaCadastro.setOnClickListener {
            startActivity(Intent(this, HomeNaoLogadaActivity::class.java))
        }

        binding.btnProximoCadastro.setOnClickListener {
            cadastrar()
        }
    }
    private fun cadastrar() {
        if (dadosValidos()) {
            val usuario = Usuario(
                binding.editEmailCadastro.text.toString(),
                binding.editSenhaCadastro.text.toString()
            )
            viewModel.cadastrarUsuario(usuario)
        }
    }

    private fun setupObservers() {
        viewModel.cadastroState.observe(this, Observer { state ->
            state.let { state ->
                when (state) {
                    is CadastroState.Sucesso -> sucessoAoCadastrar()
                    is CadastroState.Erro -> erroAoCadastrar(state.mensagem)
                }
            }
        })
    }

    private fun sucessoAoCadastrar() {
        toast("Falta Pouco")

        val intent =
            Intent(this, SegundaTelaCadastroActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun erroAoCadastrar(mensagem: String?) {
        toast("Erro ao cadastrar usuário:\n$mensagem")
    }

    private fun dadosValidos(): Boolean {
        var ok = true
        binding.textEmailCadastro.error = ""
        binding.textSenhaCadastro.error = ""
        if (binding.editEmailCadastro.text.toString().trim().isEmpty() ||
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(binding.editEmailCadastro.text.toString()).matches()) {
            binding.textEmailCadastro.error = getString(R.string.insira_um_email_valido)
            ok = false
        }
        if (binding.editSenhaCadastro.text.toString().trim().length < 6) {
            binding.textSenhaCadastro.error = getString(R.string.insira_uma_senha_valido)
            ok = false
        }
        return ok
    }

}


