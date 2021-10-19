package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityEsqueciMinhaSenhaBinding
import com.example.dont_waste_brq.activity.viewmodel.EsqueciMinhaSenhaViewModel
import androidx.appcompat.app.AlertDialog
import com.example.dont_waste_brq.data.Firebase
import com.google.android.gms.tasks.Task


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
            recuperarSenha()
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

    private fun exibirDialog(mensagem: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Redefinição de senha")
        dialog.setMessage( mensagem )
        dialog.setPositiveButton("OK") { dialogInterface, i ->

            trocarTela(LoginActivity())
            finish()

        }
        dialog.create()
        dialog.show()
    }


    private fun recuperarSenha() {

        val email = binding.editEmailSenha.text.toString().trim()
        if (email.isEmpty()) {
            binding.textEmailSenha.error = "Insira um email"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textEmailSenha.error = "Insira um e-mail válido"
        } else {
            Firebase.resetSenha(email) {
                sucesso(it)
            }
        }
    }

    private fun sucesso(task: Task<Void>) {
        if (task.isSuccessful) {
            exibirDialog("Foi encaminhado um link com redefinição de senha no e-mail cadastrado. \n" +
                    "Por favor, verifique seu e-mail.")
        } else {
            var mensagem = task.exception?.message
            if (mensagem != null) {
                if (mensagem.startsWith("There is no user record")) {
                    mensagem = "Usuário não encontrado"
                }
            }
            exibirDialog("Ops ...  \n$mensagem") // todo - arrumar aqui também
        }
    }

}
