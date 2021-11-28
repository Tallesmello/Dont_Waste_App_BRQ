package com.example.dont_waste_brq.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
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
                configuraBotao(item, holder.binding)

            }
        }

    private fun configuraBotao(
        item: AlimentoCadastrado,
        binding: ItemListProdutosCadastradosBinding,
    ) {
        binding.fabConsumido.setOnClickListener {
            item.estado.descricao = EstadoEnum.CONSUMIDO.toString()
            binding.fabConsumido.alpha = 1f
            binding.fabNeutro.alpha = 0.0f
            binding.fabDeperdicios.alpha = 0.0f
            if (binding.fabConsumido.alpha == 1f) {
                binding.chipColor.setChipBackgroundColorResource(R.color.colorGreen)

            }
        }
        binding.fabDeperdicios.setOnClickListener {
            item.estado.descricao = EstadoEnum.DESPERDICIO.toString()
            binding.fabNeutro.alpha = 0.0f
            binding.fabDeperdicios.alpha = 1f
            binding.fabConsumido.alpha = 0.0f
            if (binding.fabDeperdicios.alpha == 1f) {
                binding.chipColor.setChipBackgroundColorResource(R.color.red)
            }
        }
        binding.fabNeutro.setOnClickListener {
            item.estado.descricao = EstadoEnum.NEUTRO.toString()
            binding.fabConsumido.alpha = 0.0f
            binding.fabDeperdicios.alpha = 0.0f
            binding.fabNeutro.alpha = 1f
            binding.chipColor.setChipBackgroundColorResource(R.color.colorGreenPrimary)
        }
    }


    override fun getItemCount(): Int = list.size
}
