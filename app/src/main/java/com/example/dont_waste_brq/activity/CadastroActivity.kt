package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModel
import com.example.dont_waste_brq.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    lateinit var btnVoltarHomeCadastro: AppCompatImageView
    lateinit var btnProximo: AppCompatButton
    private lateinit var email: TextInputEditText
    private lateinit var senha: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnVoltarHomeCadastro = findViewById(R.id.btn_volta_cadastro)
        btnProximo = findViewById(R.id.btn_proximo_cadastro)
        email = findViewById(R.id.edit_email_cadastro)
        senha = findViewById(R.id.edit_senha_cadastro)


        btnVoltarHomeCadastro.setOnClickListener {
            startActivity(Intent(this, HomeNaoLogadaActivity::class.java))
        }


        btnProximo.setOnClickListener {
            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira um email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(senha.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira uma senha",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val salvarEmail: String = email.text.toString().trim { it <= ' '}
                    val salvarSenha: String = senha.text.toString().trim { it <= ' '}

                    // Usar o Login salvo no Firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(salvarEmail, salvarSenha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                Toast.makeText(
                                    this,
                                    "Falta Muito Pouco.",
                                    Toast.LENGTH_LONG
                                ).show()

                                val intent =
                                    Intent(this, SegundaTelaCadastroActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else {

                                //Se o registro não for bem sucedido eles mostram uma mensagem de erro
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }

            }
        }
    }
}