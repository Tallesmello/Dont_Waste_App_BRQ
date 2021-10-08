package com.example.dont_waste_brq.activity

import android.content.DialogInterface
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Patterns
import android.view.View
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
                exibirDialog()
            }
        }

        binding.btnVoltaHmNLogadaSenha.setOnClickListener {
            trocarTela(HomeNaoLogadaActivity())
            finish()
        }

        //FUNÇÃO VALIDAR EMAIL VALIDO
        //  private fun validarEmailValido ()
//            if (viewModel.mensagemToast(this, "Senha enviada com sucesso"))
//
//    }else{
//            viewModel.mensagemToast(this, "E-mail Inválido")
//        }

    }

    private fun exibirDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Redefinição de senha")
        dialog.setMessage(
            "Foi encaminhado um link com redefinição de senha no e-mail cadastrado. \n" +
                    "Por favor, verifique seu e-mail."
        )
        dialog.setPositiveButton("OK") { dialogInterface, i ->

            trocarTela(LoginActivity())
            finish()

        }
        dialog.create()
        dialog.show()
    }


    private fun recuperarSenha(): Boolean {

        var result = false
        var email = binding.editEmailSenha.text.toString().trim()
        if (email.isEmpty()) {
            binding.textEmailSenha.error = "Insira um email"
            result = false
        } else if (!Patterns.EMAIL_ADDRESS
                .matcher(email).matches()
        ) {
            binding.textEmailSenha.error = "Insira um e-mail válido"
            result = false
        } else {
            Firebase.resetSenha(email, this, binding.textEmailSenha)
            result = true
        }
        return result
    }

}
