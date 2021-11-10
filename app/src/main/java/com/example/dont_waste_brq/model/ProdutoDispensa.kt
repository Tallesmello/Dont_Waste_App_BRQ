package com.example.dont_waste_brq.model

data class ProdutoDispensa(
    val nome : String,
    val qunatidade : Int = 0
){
    companion object Geladeira{
        const val DISPENSA = "Dispensa"
    }
}