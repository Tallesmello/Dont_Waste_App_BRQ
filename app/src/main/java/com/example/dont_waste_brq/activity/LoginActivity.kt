package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityLoginBinding
import com.example.dont_waste_brq.activity.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val email = binding.editEmailLogin.text.toString()
        val senha = binding.editSenhaLogin.text.toString()

        binding.btnLoginTelaLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                    viewModel.mensagemToast(this, "Por favor insira um email")
                }

                TextUtils.isEmpty(senha.trim { it <= ' ' }) -> {
                    viewModel.mensagemToast(this, "Por favor insira uma senha")
                }
                else -> {
                    val (emailValidado: String, senhaValidado: String) = viewModel.validandoEmailSenhaLogin(
                        email,
                        senha
                    )
                    // Usar o Login salvo no Firebase
                    autenticacaoEmailESenhaFirebase(emailValidado, senhaValidado)
                }

            }
            //vai para tela esqueci minha senha
            binding.btnEsqueciSenhaLogin.setOnClickListener {
                viewModel.trocandoTelaPara(this,EsqueciMinhaSenhaActivity())
                finish()
            }
        }
    }



    private fun autenticacaoEmailESenhaFirebase(email: String, senha: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.mensagemToast(this, "Você Logou com sucesso.")
                    val intent =
                        Intent(this, HomeLogadaActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                    intent.putExtra("email_id", email)
                    startActivity(intent)
                    finish()
                } else {

                    //Se o registro não for bem sucedido eles mostram uma mensagem de erro
                    viewModel.mensagemToast(this, task.exception!!.message.toString())
                }
            }
    }


}

