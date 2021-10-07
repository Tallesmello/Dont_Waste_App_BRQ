package com.example.dont_waste_brq.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityEsqueciMinhaSenhaBinding
import com.example.dont_waste_brq.activity.viewmodel.EsqueciMinhaSenhaViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.data.Firebase


class EsqueciMinhaSenhaActivity : BaseActivity() {

    private lateinit var viewModel: EsqueciMinhaSenhaViewModel
    private lateinit var binding: ActivityEsqueciMinhaSenhaBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciMinhaSenhaBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EsqueciMinhaSenhaViewModel::class.java)
        val view = binding.root
        setContentView(view)


        binding.btnLoginTelaSenha.setOnClickListener {
            if (recuperarSenha()) {
                trocarTela(LoginActivity())

                finish()
            }
        }

        //FUNÇÃO VALIDAR EMAIL VALIDO
        //  private fun validarEmailValido ()
//            if (viewModel.mensagemToast(this, "Senha enviada com sucesso"))
//
//    }else{
//            viewModel.mensagemToast(this, "E-mail Inválido")
//        }

    }

    private fun recuperarSenha() : Boolean {

        var result = false
        var email = binding.editEmailSenha.text.toString().trim()
        if (email.isEmpty()) {
            binding.textEmailSenha.error = "Insira um email"
            result = false
        } else {
            Firebase.resetSenha(email, this, binding.textEmailSenha)
            result = true
        }
            return result
    }

//    private fun enviarEmail( email : String) {
//
//        try {
//            Firebase.firebaseAuth.sendPasswordResetEmail(email)
//                .addOnSuccessListener { task ->
//                    Toast.makeText(this, "Encaminhamos um email", Toast.LENGTH_LONG).show()
//                }
//
//                .addOnFailureListener{
//                    binding.textEmailSenha.error = "Email Inválido"
//                }
//        } catch (e:  Exception) {
//            e.printStackTrace()
//        }
//    }
}
