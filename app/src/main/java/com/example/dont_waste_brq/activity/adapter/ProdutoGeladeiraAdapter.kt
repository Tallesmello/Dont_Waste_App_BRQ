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
import com.example.dont_waste_brq.model.ProdutoGeladeira

class ProdutoGeladeiraAdapter(
    val itens: ArrayList<ProdutoGeladeira>
) : RecyclerView.Adapter<ProdutoGeladeiraViewHolder>() {

    private var houveAtualizacao = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoGeladeiraViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_geladeira, parent, false)
        return ProdutoGeladeiraViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ProdutoGeladeiraViewHolder, position: Int) {
        itens[position].apply {
           holder.bind(this, getInstanceRemover() )
        }
    }

    private fun getInstanceRemover(): Remover {
        return object : Remover {
            override fun executar(item: ProdutoGeladeira) {
                removerItem(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removerItem(item: ProdutoGeladeira) {
        itens.remove(item)
        notifyDataSetChanged()
        houveAtualizacao = true
    }

    fun adicionarItem(item: ProdutoGeladeira) {
        itens.add(item)
        notifyItemInserted(itens.size)
        houveAtualizacao = true
    }

    fun houveAtualizacao() = houveAtualizacao

}

class ProdutoGeladeiraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        item : ProdutoGeladeira,
        remover: Remover
    ) {
        val nome = itemView.findViewById<TextView>(R.id.tv_descricao)
        nome.text = item.nome
        val quantidade = itemView.findViewById<TextView>(R.id.contador_item_frutas)
        quantidade.text = item.quantidade.toString()

        val buttonRemover = itemView.findViewById<ImageButton>(R.id.imageButton_remover)
        buttonRemover.setOnClickListener {
            if (item.quantidade > 0) {
                item.quantidade -= 1
                quantidade.text = item.quantidade.toString()
            }
            else {
               exibirDialog(itemView.context, item, remover)
            }
        }

        val buttonAdicionar = itemView.findViewById<ImageButton>(R.id.imageButton_adicionar)
        buttonAdicionar.setOnClickListener {
            item.quantidade += 1
            quantidade.text = item.quantidade.toString()
        }
    }

    private fun exibirDialog(
        context: Context,
        item: ProdutoGeladeira,
        remover: Remover
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Remover item")
        dialog.setMessage("Deseja remover o(a) ${item.nome} ?")
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
    fun executar(item: ProdutoGeladeira)
}