package com.example.dont_waste_brq.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityEsqueciMinhaSenhaBinding
import com.example.dont_waste_brq.activity.viewmodel.EsqueciMinhaSenhaViewModel
import com.example.dont_waste_brq.model.Usuario

class EsqueciMinhaSenhaActivity : BaseActivity() {

    private lateinit var viewModel: EsqueciMinhaSenhaViewModel
    private lateinit var binding: ActivityEsqueciMinhaSenhaBinding
    private lateinit var recuperado : Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciMinhaSenhaBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EsqueciMinhaSenhaViewModel::class.java)
        val view = binding.root
        setContentView(view)


        binding.btnLoginTelaSenha.setOnClickListener {

            trocarTela(LoginActivity())
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
}
