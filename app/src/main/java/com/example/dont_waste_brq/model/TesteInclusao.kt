package com.example.dont_waste_brq.model

import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
//isso e uma classe estatica que fiz pra simular os dados sendo salvoss
object TesteInclusao {
     val conteudo = ConteudoInterno("Banana",30,"25/10/2012","Pra sempre")
    val armazenar = Armazenar("Geladeira",TipoConteudoEnum.FRUTAS)
}