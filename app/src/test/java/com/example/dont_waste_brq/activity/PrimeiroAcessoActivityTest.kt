package com.example.dont_waste_brq.activity

import com.example.dont_waste_brq.model.Usuario
import com.example.dont_waste_brq.util.helper.ValidarCampoHelper.emailValido
import com.example.dont_waste_brq.util.helper.ValidarCampoHelper.senhaValida
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PrimeiroAcessoActivityTest {

    private val activity : PrimeiroAcessoActivity = mockk()
    private val user = Usuario("teste@gmail.com", "345674")

    @Test
    fun quandoSenhaEEmailEstiverCorreto_retornarTrue(){
        val ok = senhaValida(user.senha)
        every { activity.validarDados(user.email, user.senha) } returns ok
        assertTrue(
            activity.validarDados(user.email, user.senha)
        )
    }
    @Test
    fun quandoEmailForVazioOuFormatoErrado_retornarFalse() {
        val email = "teste.com"
        val senha = "345678"
        val ok = emailValido(email)
        every {
            activity.validarDados(email, senha)
        } returns ok
        assertFalse(activity.validarDados(email, senha))
    }


    @Test
    fun quandoSenhaTiverMenosQueSeisDigitos_retornarFalse(){
        val senha = "345"
        val ok = senhaValida(senha)
        every {
            activity.validarDados(user.email, senha)
        } returns ok
        assertFalse(activity.validarDados(user.email, senha))
    }

    @Test
    fun quandoSenhaEstiverVazia_retornarFalse(){
        val senha = ""
        val ok = senhaValida(senha)
        every {
            activity.validarDados(user.email, senha)
        } returns ok
        assertFalse(activity.validarDados(user.email, senha))
    }

    @Test
    fun quandoSenhaConterMaisDeOitoDigitos_retornarFalse(){
        val senha = "1234567890"
        val ok = senhaValida(senha)
        every { activity.validarDados(user.email, senha) } returns ok
        assertFalse(activity.validarDados(user.email, senha))
    }

    @Test
    fun quandoEmailForVazio_retornarFalse(){
        val email = ""
        val ok = emailValido(email)
        every { activity.validarDados(email, user.senha) } returns ok
        assertFalse(activity.validarDados(email, user.senha))
    }

    @Test
    fun quandoEmailTiverForaDaFormatacaoCorreta_retornarFalse(){
        val email = "test.com"
        val ok = emailValido(email)
        every { activity.validarDados(email, user.senha) } returns ok
        assertFalse(activity.validarDados(email, user.senha))
    }

}