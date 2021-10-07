package com.example.dont_waste_brq.activity.enum

enum class FrequenciaEnum (val descricao: String){
    SEMANALMENTE("Semanalmente"),
    QUINZENALMENTE("Quinzenalmente"),
    MENSALMENTE("Mensalmente");
    companion object {

        fun getArrayListOfFrequencia () : ArrayList<String> {
            val frequencia = ArrayList <String> ()
            FrequenciaEnum.values().forEach {
                frequencia.add(it.descricao)
            }
            return frequencia
        }

        fun getEnumFromFrequencia (descricao: String) : FrequenciaEnum?{
            return FrequenciaEnum.values().filter {
                it.descricao == descricao

            }
                .firstOrNull()
        }
    }
}
