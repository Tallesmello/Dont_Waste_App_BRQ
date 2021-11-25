package com.example.dont_waste_brq.activity.adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.ItensProdutosActivity
import com.example.dont_waste_brq.activity.adapter.AlimentoCadastradoAdapter.*
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.databinding.ItemListProdutosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado

class AlimentoCadastradoAdapter(val list: List<AlimentoCadastrado>) : RecyclerView.Adapter<AlimentoCadastradoViewHolder>() {

    inner class AlimentoCadastradoViewHolder(val binding: ItemListProdutosCadastradosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlimentoCadastradoViewHolder {
        return AlimentoCadastradoViewHolder(
            ItemListProdutosCadastradosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

        override fun onBindViewHolder(holder: AlimentoCadastradoViewHolder, position: Int) {
            val item = list[position]
            holder.binding.apply {
                tvNomeAlimentoCadastrado.text = item.nome
                itemProdutosCadastradoData.text = item.data
                contadorItemFrutas.text = item.quantidade
                fabConsumido.setOnClickListener {
                    item.estado.descricao = EstadoEnum.CONSUMIDO.toString()
                    fabConsumido.alpha = 1f
                    fabNeutro.alpha = 0.0f
                    fabDeperdicios.alpha = 0.0f
                    if (fabConsumido.alpha == 1f) {
                        chipColor.setChipBackgroundColorResource(R.color.colorGreen)

                    }
                }
                fabDeperdicios.setOnClickListener {
                    item.estado.descricao = EstadoEnum.DESPERDICIO.toString()
                    fabNeutro.alpha = 0.0f
                    fabDeperdicios.alpha = 1f
                    fabConsumido.alpha = 0.0f
                    if(fabDeperdicios.alpha == 1f) {
                        chipColor.setChipBackgroundColorResource(R.color.red)
                    }
                }
                fabNeutro.setOnClickListener {
                    item.estado.descricao = EstadoEnum.NEUTRO.toString()
                    fabConsumido.alpha = 0.0f
                    fabDeperdicios.alpha = 0.0f
                    fabNeutro.alpha = 1f
                    chipColor.setChipBackgroundColorResource(R.color.colorGreenPrimary)
                }

            }
        }
//            val colorChip = item.estado.descricao
//            chipColor.chipBackgroundColor = verificaCor(holder.itemView, colorChip)



//    private fun verificaCor(itemView: View, colorChip: Int): ColorStateList? {
//        if (colorChip == R.color.colorGreenPrimary) {
//
//        }
//    }



    override fun getItemCount(): Int = list.size
}
