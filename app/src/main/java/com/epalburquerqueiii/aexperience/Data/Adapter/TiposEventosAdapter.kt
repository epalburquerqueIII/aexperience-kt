package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.TiposEvento
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_tiposevento.view.*


class TiposEventosAdapter(private val tiposeventos: ArrayList<TiposEvento>, context: Context) : RecyclerView.Adapter<TiposEventosAdapter.TiposeventoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiposeventoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_tiposevento, parent, false)
        return TiposeventoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return tiposeventos.size
    }

    override fun onBindViewHolder(holder: TiposeventoViewHolder, position: Int) {
       val itemnotes = tiposeventos[position]
        holder.bindView(itemnotes)

    }

    inner class TiposeventoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var TiposEvento:TiposEvento? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(tiposeventos[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Tiposevento: TiposEvento){
            this.TiposEvento = Tiposevento
            itemView.Nombre_item.text = this.TiposEvento?.Nombre
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Tiposevento: TiposEvento)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}