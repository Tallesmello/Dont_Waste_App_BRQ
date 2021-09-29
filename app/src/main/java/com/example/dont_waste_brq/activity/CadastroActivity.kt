package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityCadastroBinding
import com.example.dont_waste_brq.viewmodel.CadastroViewModel
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : BaseActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var viewModel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val email = binding.editEmailCadastro.text.toString()
        val senha = binding.editEmailCadastro.text.toString()
        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)
        iniciandoBotoes(senha, email)
    }

    private fun iniciandoBotoes(senha: String, email: String) {
        binding.btnVoltaCadastro.setOnClickListener {
            trocandoTelaPara(HomeNaoLogadaActivity())
            finish()
        }
        binding.btnAjudaCadastro.setOnClickListener {
            mensagem("A senha deve conter de 6 a 8 caracteres numéricos")
        }
        binding.btnProximoCadastro.setOnClickListener {
            validacao(senha, email)
        }
    }

    private fun validacao(senha: String, email: String) {
        when {
            TextUtils.isEmpty(senha.trim { it <= ' ' }) -> {
                viewModel.notificacao("Por favor insira um email", this)
            }
            TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                viewModel.notificacao("Por favor insira uma senha", this)
            }
            else -> {
                val (salvarEmail: String, salvarSenha: String) = viewModel.validanddoEmailESenha(
                    email,
                    senha
                )
                // Usar o Login salvo no Firebase
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(salvarEmail, salvarSenha)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            sucesso(this)
                        } else {
                            //Se o registro não for bem sucedido eles mostram uma mensagem de erro
                            viewModel.notificacao(task.exception!!.message.toString(), this)
                        }
                    }
            }

        }
    }

    private fun sucesso(context: Context) {
        viewModel.notificacao("Falta Pouco", context)
        startActivity(viewModel.trocandoTelaPara(context, SegundaTelaCadastroActivity()))
        finish()
    }


}