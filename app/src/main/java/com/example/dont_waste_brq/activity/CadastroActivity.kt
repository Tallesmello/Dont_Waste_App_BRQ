package com.example.dont_waste_brq.activity

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

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var viewModel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)

        binding.btnVoltaCadastro.setOnClickListener {
            startActivity(viewModel.trocandoTelaPara(this, HomeNaoLogadaActivity()))
            finish()
        }


        binding.btnAjudaCadastro.setOnClickListener {
           viewModel.notificacaoPersonalizada(this, "A senha deve conter de 6 a 8 caracteres numéricos")
        }

        binding.btnProximoCadastro.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.editEmailCadastro.text.toString().trim { it <= ' ' }) -> {
                    viewModel.notificacao("Por favor insira um email", this)
                }
                TextUtils.isEmpty(binding.editSenhaCadastro.text.toString().trim { it <= ' ' }) -> {
                    viewModel.notificacao("Por favor insira uma senha", this)
                }
                else -> {
                    val salvarEmail: String = binding.editEmailCadastro.text.toString().trim { it <= ' '}
                    val salvarSenha: String = binding.editSenhaCadastro.text.toString().trim { it <= ' '}

                    // Usar o Login salvo no Firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(salvarEmail, salvarSenha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                viewModel.notificacao("Falta Pouco", this)

                                val intent =
                                    Intent(this, SegundaTelaCadastroActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else {
                                //Se o registro não for bem sucedido eles mostram uma mensagem de erro
                                viewModel.notificacao(task.exception!!.message.toString(), this)
                            }
                        }
                }

            }
        }
    }




}