package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBono
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_consumobono.view.*
import kotlinx.android.synthetic.main.item_consumobono.view.*


class ConsumoBonosAdapter(private val consumoBonos: ArrayList<ConsumoBono>, context: Context) : RecyclerView.Adapter<ConsumoBonosAdapter.ConsumoBonoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumoBonoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_consumobono, parent, false)
        return ConsumoBonoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return consumoBonos.size
    }

    override fun onBindViewHolder(holder: ConsumoBonoViewHolder, position: Int) {
       val itemnotes = consumoBonos[position]
        holder.bindView(itemnotes)

    }

    inner class ConsumoBonoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var ConsumoBono:ConsumoBono? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(consumoBonos[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(ConsumoBono: ConsumoBono){
            this.ConsumoBono = ConsumoBono
            itemView.fecha_item.text = this.ConsumoBono?.Fecha
            itemView.sesiones_item.text = this.ConsumoBono?.Sesiones.toString()
            itemView.usuario_item.text = this.ConsumoBono?.NombreUsuario
        }

    }

    interface OnItemClickListener {
        fun onItemClick(ConsumoBono: ConsumoBono)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}