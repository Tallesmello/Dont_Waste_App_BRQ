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
import com.example.dont_waste_brq.activity.enum.TipoConteudoEnum
import com.example.dont_waste_brq.data.RealtimeDatabase
import com.example.dont_waste_brq.model.ProdutoGeladeira
import com.example.dont_waste_brq.repository.dao.GeladeiraDAO

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
           holder.bind(this, getInstanceRemover()) { atualizou() }
        }
    }

    private fun getInstanceRemover(tipo : TipoConteudoEnum): Remover {
        return object : Remover {
            override fun executar(item: ProdutoGeladeira,tipo : TipoConteudoEnum) {
                removerItem(item,tipo)
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removerItem(item: ProdutoGeladeira,tipo: TipoConteudoEnum) {
        val dao = GeladeiraDAO(tipo)
        dao.removerItem(item.id)
        itens.remove(item)
        notifyDataSetChanged()
        atualizou()
    }

    fun adicionarItem(item: ProdutoGeladeira) {
        itens.add(item)
        notifyItemInserted(itens.size)
        atualizou()
    }

    fun houveAtualizacao() = houveAtualizacao

    fun resetAtualizacoes() {
        // será usado se/quando as atualizações forem salvas e
        // continuar na mesma activity
        houveAtualizacao = false
    }

    private fun atualizou() { houveAtualizacao = true }
}

class ProdutoGeladeiraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        item: ProdutoGeladeira,
        remover: Remover,
        atualizou : () -> Unit,
        tipo : TipoConteudoEnum
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
                atualizou()
            }
            else {
               exibirDialog(itemView.context, item, remover,tipo)
            }
        }

        val buttonAdicionar = itemView.findViewById<ImageButton>(R.id.imageButton_adicionar)
        buttonAdicionar.setOnClickListener {
            item.quantidade += 1
            quantidade.text = item.quantidade.toString()
            atualizou()
        }
    }

    private fun exibirDialog(
        context: Context,
        item: ProdutoGeladeira,
        remover: Remover,
        tipo : TipoConteudoEnum
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Remover item")
        dialog.setMessage("Deseja remover o(a) ${item.nome} ?")
        dialog.setPositiveButton("Sim") { dialogInterface, i ->
            remover.executar(item,tipo)
        }
        dialog.setNegativeButton("Não") { dialogInterface, i ->
        }
        dialog.create()
        dialog.show()
    }
}

interface Remover {
    fun executar(item: ProdutoGeladeira,tipo : TipoConteudoEnum)
}