package com.example.dont_waste_brq.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.model.Itens

internal class AdapterItensFrutas(
    val itens: List<Itens>
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_geladeira, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itens[position].apply {
           holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }
}


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

   private lateinit var context : Context


    fun bind(item : Itens) {
        val nome = itemView.findViewById<TextView>(R.id.tv_descricao)
        nome.text = item.nomeItem
        val quantidade = itemView.findViewById<TextView>(R.id.contador_item_frutas)
        quantidade.text = item.quantidadeItem.toString()
        val buttonRemover = itemView.findViewById<ImageButton>(R.id.imageButton_remover)
        buttonRemover.setOnClickListener {
            if (item.quantidadeItem > 0) {
                item.quantidadeItem -= 1
                quantidade.text = item.quantidadeItem.toString()
            }
            else {
               exibirDialog()
            }
        }
        val buttonAdicionar = itemView.findViewById<ImageButton>(R.id.imageButton_adicionar)
        buttonAdicionar.setOnClickListener {
            item.quantidadeItem += 1
            quantidade.text = item.quantidadeItem.toString()
        }
    }

    private fun exibirDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Remover item")
        dialog.setMessage("Deseja remover o item")
        dialog.setPositiveButton("Sim") { dialogInterface, i ->
        }
        dialog.setNegativeButton("Não") { dialogInterface, i ->
        }
        dialog.create()
        dialog.show()
    }
}