package com.example.dont_waste_brq.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dont_waste_brq.databinding.ActivityEsqueciMinhaSenhaBinding
import com.example.dont_waste_brq.viewmodel.EsqueciMinhaSenhaViewModel

class EsqueciMinhaSenhaActivity : AppCompatActivity() {

    private lateinit var viewModel: EsqueciMinhaSenhaViewModel
    private lateinit var binding: ActivityEsqueciMinhaSenhaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciMinhaSenhaBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(EsqueciMinhaSenhaViewModel::class.java)
        val view = binding.root
        setContentView(view)


        binding.btnLoginTelaSenha.setOnClickListener {
            viewModel.trocandoTelaPara(this, LoginActivity())
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
