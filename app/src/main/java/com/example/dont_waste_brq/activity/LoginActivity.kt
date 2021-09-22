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
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var btnVoltarHomeLogin: AppCompatImageView
    lateinit var btnLogar: AppCompatButton
    lateinit var emailLogin: TextInputEditText
    lateinit var senhaLogin: TextInputEditText
    lateinit var btnEsqueciMinhaSenha: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnVoltarHomeLogin = findViewById(R.id.btn_volta_hm_n_logada_login)
        btnLogar = findViewById(R.id.btn_Login_tela_login)
        btnEsqueciMinhaSenha = findViewById(R.id.btn_esqueci_senha_login)
        emailLogin = findViewById(R.id.edit_email_login)
        senhaLogin = findViewById(R.id.edit_senha_login)


        btnLogar.setOnClickListener {
            when {
                TextUtils.isEmpty(emailLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira um email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(senhaLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Por favor insira uma senha",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email: String = emailLogin.text.toString().trim { it <= ' '}
                    val senha: String = senhaLogin.text.toString().trim { it <= ' '}

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