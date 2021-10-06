package com.example.dont_waste_brq.activity.enum

enum class QtdPessoasEnum(val descricao: String) {
    UM("Um"),
    DOIS("Dois"),
    TRES("Três"),
    TRES_OU_MAIS("Três ou mais");
    companion object {

        fun getArrayListOfDescricao () : ArrayList<String> {
            val qtdPessoas = ArrayList <String> ()
            values().forEach {
                qtdPessoas.add(it.descricao)
            }
            return qtdPessoas
        }

        fun getEnumFromDescricao (descricao: String) : QtdPessoasEnum?{
            return values().filter {
                it.descricao == descricao

            }
                .firstOrNull()
        }
    }

}