package com.example.dont_waste_brq.activity.enum

enum class LocalEnum(val descricao: String) {

    GELADEIRA("GELADEIRA"),
    DISPENSA("DISPENSA"),
    CONSUMO("CONSUMO");
    companion object {

        fun getArrayListOfLocal () : ArrayList<String> {
            val local = ArrayList <String> ()
            LocalEnum.values().forEach {
                local.add(it.descricao)
            }
            return local
        }

        fun getEnumFromLocal (descricao: String) : FrequenciaEnum?{
            return FrequenciaEnum.values().filter {
                it.descricao == descricao
            }.firstOrNull()
        }
    }
}