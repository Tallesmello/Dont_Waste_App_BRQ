package com.example.dont_waste_brq.activity.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.model.Produto

class ProdutoAdapter(
    val itens: ArrayList<Produto?>
) : RecyclerView.Adapter<ProdutoViewHolder>() {

    private var houveAtualizacao = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_geladeira, parent, false)
        return ProdutoViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        itens[position].apply {
            holder.bind(this, getInstanceRemover()) { atualizou() }
        }
    }

    private fun getInstanceRemover(): Remover {
        return object : Remover {
            override fun executar(item: Produto?) {
                removerItem(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removerItem(item: Produto?) {
        itens.remove(item)
        notifyDataSetChanged()
        atualizou()
    }

    fun adicionarItem(item: Produto) {
        itens.add(item)
        notifyItemInserted(itens.size)
        atualizou()
    }

    fun houveAtualizacao() = houveAtualizacao
    fun resetAtualizacoes() {
        houveAtualizacao = false
    }

    private fun atualizou() { houveAtualizacao = true }
}

class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        item: Produto?,
        remover: Remover,
        atualizou : () -> Unit
    ) {
        val nome = itemView.findViewById<TextView>(R.id.tv_descricao)
        nome.text = item?.nome
        val quantidade = itemView.findViewById<TextView>(R.id.contador_item_frutas)
        quantidade.text = item?.quantidade.toString()

        val buttonRemover = itemView.findViewById<ImageButton>(R.id.imageButton_remover)
        buttonRemover.setOnClickListener {
            item?.let { item ->
                if (item.quantidade > 0) {
                    item.quantidade -= 1
                    quantidade.text = item.quantidade.toString()
                    atualizou()
                }
                else {
                    exibirDialog(itemView.context, item, remover)
                }
            }
        }
        val buttonAdicionar = itemView.findViewById<ImageButton>(R.id.imageButton_adicionar)
        buttonAdicionar.setOnClickListener {
            item?.let { item.quantidade += 1 }
            quantidade.text = item?.quantidade.toString()
            atualizou()
        }
    }

    private fun exibirDialog(
        context: Context,
        item: Produto?,
        remover: Remover
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Remover item")
        dialog.setMessage("Deseja remover o(a) ${item?.nome} ?")
        dialog.setPositiveButton("Sim") { dialogInterface, i ->
            remover.executar(item)
        }
        dialog.setNegativeButton("Não") { dialogInterface, i ->
        }
        dialog.create()
        dialog.show()
    }
}

interface Remover {
    fun executar(item: Produto?)
}