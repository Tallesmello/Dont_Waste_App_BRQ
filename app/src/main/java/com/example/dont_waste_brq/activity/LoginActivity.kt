package com.example.dont_waste_brq.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityLoginBinding
import com.example.dont_waste_brq.viewmodel.LoginViewModel
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

        binding.btnLoginTelaLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.editEmailLogin.text.toString().trim { it <= ' ' }) -> {
                    viewModel.mensagemToast(this, "Por favor insira um email")
                }

                TextUtils.isEmpty(binding.editSenhaLogin.text.toString().trim { it <= ' ' }) -> {
                    viewModel.mensagemToast(this, "Por favor insira uma senha")
                }
                else -> {
                    val email: String = binding.editEmailLogin.text.toString().trim { it <= ' ' }
                    val senha: String = binding.editSenhaLogin.text.toString().trim { it <= ' ' }
                    // Usar o Login salvo no Firebase
                    autenticacaoEmailESenhaFirebase(email, senha)
                }

            }
        }
    }

    private fun autenticacaoEmailESenhaFirebase(email: String, senha: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    viewModel.mensagemToast(this, "Você Logou com sucesso.")

                    /**
                     * Aqui, o novo usuário gerado é automaticamente inscrito, então nós apenas saímos e o enviamos para a
                     * tela principal com id e e-mail que o usuário usou para se cadastrar.
                     */
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

