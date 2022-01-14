package com.example.dont_waste_brq.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class UsuarioTest {

    var user = Usuario("teste@gmail.com", "123456")

    @Test
    fun getEmail() {
        val emailDevolvido = user.email
        assertEquals("teste@gmail.com", emailDevolvido)
    }

    @Test
    fun getSenha() {
        val senhaDevolvida = user.senha
        assertEquals("123456", senhaDevolvida)
    }

    @Test
    fun setEmail(){
        user.email = "testesdois@gmail.com"
        assertEquals("testesdois@gmail.com", user.email)
    }
}