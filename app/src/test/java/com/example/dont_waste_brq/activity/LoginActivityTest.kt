package com.example.dont_waste_brq.activity

import com.example.dont_waste_brq.util.helper.ValidarCampoHelper.emailValido
import com.example.dont_waste_brq.util.helper.ValidarCampoHelper.senhaValida
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class LoginActivityTest {

    private val activity: LoginActivity = mockk()

    @Test
    fun `retorna verdadeiro quando dados forem válidos`() {
        val email = "jose@gmail.com"
        val senha = "123456"
        val ok = emailValido(email) && senhaValida(senha)
        every { activity.validarDados(email, senha) } returns ok
        assertTrue(
            activity.validarDados(email, senha)
        )
    }

    @Test
    fun `retorna falso quando senha for inválida`() {
        val email = "jose@gmail.com"
        val senha = "123"
        val ok = emailValido(email) && senhaValida(senha)
        every { activity.validarDados(email, senha) } returns ok
        assertFalse(
            activity.validarDados(email, senha)
        )
    }

    @Test
    fun `retorna falso quando email for inválido`() {
        val email = "jose.gmail.com"
        val senha = "123456"
        val ok = emailValido(email) && senhaValida(senha)
        every { activity.validarDados(email, senha) } returns ok
        assertFalse(
            activity.validarDados(email, senha)
        )
    }

}