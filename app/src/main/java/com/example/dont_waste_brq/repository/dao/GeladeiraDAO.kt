package com.example.dont_waste_brq.repository.dao

import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.model.Armazenar
import com.example.dont_waste_brq.model.Produto
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.google.android.gms.tasks.Task

class GeladeiraDAO(
    tipo : TipoConteudoEnum
) : ItemDAO(
    Armazenar(ProdutoGeladeira.GELADEIRA, tipo)
)