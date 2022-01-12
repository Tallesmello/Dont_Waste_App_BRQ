package com.example.dont_waste_brq.util.helper

import org.junit.Assert.*
import org.junit.Test

class ValidarCampoHelperTest {
    @Test
    fun `em emailValido quando email estiver vazio deve retornar falso`(){
        val email = ""
        assertFalse(ValidarCampoHelper.emailValido(email))
    }

    @Test
    fun `em emailValido quando email estiver errado deve retornar falso`(){
        val email1 = "maria"
        val email2 = "maria@gmail"
        val email3 = "maria.gmail.com"
        assertFalse(ValidarCampoHelper.emailValido(email1))
        assertFalse(ValidarCampoHelper.emailValido(email2))
        assertFalse(ValidarCampoHelper.emailValido(email3))
    }

    @Test
    fun `em emailValido quando email estiver correto deve retornar verdadeiro`(){
        val email1 = "maria@gmail.com"
        val email2 = "jose@hotmail.com.br"
        assertTrue(ValidarCampoHelper.emailValido(email1))
        assertTrue(ValidarCampoHelper.emailValido(email2))
    }

    @Test
    fun `em senhaValida quando senha estiver incorreta deve retornar falso`(){
        val senha1 = ""
        val senha2 = "123"
        assertFalse(ValidarCampoHelper.senhaValida(senha1))
        assertFalse(ValidarCampoHelper.senhaValida(senha2))
    }

    @Test
    fun `em senhaValida quando senha estiver correta deve retornar verdadeiro`(){
        val senha1 = "senha123"
        val senha2 = "Brq@123"
        assertTrue(ValidarCampoHelper.senhaValida(senha1))
        assertTrue(ValidarCampoHelper.senhaValida(senha2))
    }

    @Test
    fun `em frequenciaCompraValida quando valor estiver vazio deve retornar falso`(){
        val freq = ""
        assertFalse(ValidarCampoHelper.frequenciaCompraValida(freq))
    }

    @Test
    fun `em frequenciaCompraValida quando valor estiver incorreto deve retornar falso`(){

    }

    @Test
    fun `em frequenciaCompraValida quando valor estiver correto deve retornar verdadeiro`(){

    }
}