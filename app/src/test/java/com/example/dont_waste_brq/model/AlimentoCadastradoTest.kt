package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.AlimentosCadastradosActivity
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class AlimentoCadastradoTest {

    val alimento = AlimentoCadastrado("Abacaxi", 3, 4, "12/9", EstadoEnum.DESPERDICIO)
    private val activity : AlimentosCadastradosActivity = mockk()

    @Test
    fun getNome() {
        val nomeDevolvido = alimento.nome
        assertEquals("Abacaxi", nomeDevolvido)
    }

    @Test
    fun getQuantidade() {
        val quantidadeDevolvida = alimento.quantidade
        assertEquals(3, quantidadeDevolvida)
    }

    @Test
    fun getEstoque() {
        val estoqueDevolvido = alimento.estoque
        assertEquals(4, estoqueDevolvido)
    }

    @Test
    fun getData() {
        val dataDevolvido = alimento.data
        assertEquals("12/9", dataDevolvido)
    }

    @Test
    fun getEstado() {
        val estadoDevolvido = alimento.estado
        assertEquals(EstadoEnum.DESPERDICIO, estadoDevolvido)
    }


}