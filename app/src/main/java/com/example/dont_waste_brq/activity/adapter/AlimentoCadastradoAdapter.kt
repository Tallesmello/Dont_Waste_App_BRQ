package com.example.dont_waste_brq.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.custom_view.SetEstadoInterface
import com.example.dont_waste_brq.databinding.ItemListProdutosCadastradosBinding
import com.example.dont_waste_brq.model.AlimentoCadastrado

class AlimentoCadastradoAdapter(
    val list: List<AlimentoCadastrado>
    ) : RecyclerView.Adapter<AlimentoCadastradoViewHolder>() {

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
        if (item.estoque == 0) {
            holder.itemView.visibility = View.GONE
        }
        holder.binding.apply {
            tvNomeAlimentoCadastrado.text = item.nome
            itemProdutosCadastradoData.text = item.data.subSequence(0, 5)
            contadorItemFrutas.text = item.quantidade.toString()
            configurarMaisEMenos(holder, item)
            cdview.estado = object : SetEstadoInterface {
                override fun setEstado(estadoEnum: EstadoEnum) {
                    item.estado = estadoEnum
                }
            }
        }
    }

    private fun configurarMaisEMenos(
        holder: AlimentoCadastradoViewHolder,
        item: AlimentoCadastrado
    ) {
        val binding = holder.binding
        val quantidade = binding.contadorItemFrutas
        quantidade.text = item.quantidade.toString()
        binding.imageButtonAdicionar.setOnClickListener {
            if (item.quantidade < item.estoque) {
                item.quantidade += 1
                quantidade.text = item.quantidade.toString()
            } else {
                mensagem(holder.itemView.context,
                    "quantidade deve ser menor ou igual ao estoque")
            }
        }
        binding.imageButtonRemover.setOnClickListener {
            if (item.quantidade > 0) {
                item.quantidade -= 1
                quantidade.text = item.quantidade.toString()
            } else {
                mensagem(holder.itemView.context,
                    "quantidade deve ser maior ou igual a zero")
            }
        }

    }

    private fun mensagem(context: Context, mensagem: String) {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int = list.size
}

class AlimentoCadastradoViewHolder(
    val binding: ItemListProdutosCadastradosBinding
) : RecyclerView.ViewHolder(binding.root)
