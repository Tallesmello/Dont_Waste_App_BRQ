package com.example.dont_waste_brq.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnVoltaHmNLogadaLogin.setOnClickListener {
            finish()
        }

        binding.btnLoginTelaLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.editEmailLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira um email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(binding.editSenhaLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira uma senha",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email: String = binding.editEmailLogin.text.toString().trim { it <= ' '}
                    val senha: String = binding.editSenhaLogin.text.toString().trim { it <= ' '}

                    // Usar o Login salvo no Firebase
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                Toast.makeText(
                                    this,
                                    "Você Logou com sucesso.",
                                    Toast.LENGTH_LONG
                                ).show()

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