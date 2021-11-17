package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.LocalEnum
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum

data class Armazenar(
    var local : LocalEnum,//opcao da tela categoria
    var tipoConteudo : TipoConteudoEnum//opcao da tela geladeira ou dispensa
)