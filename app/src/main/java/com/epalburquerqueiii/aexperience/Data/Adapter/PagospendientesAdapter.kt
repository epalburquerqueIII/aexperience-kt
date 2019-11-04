package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Pagopendiente
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_pagopendiente.view.*


class PagospendientesAdapter(private val pagospendientes: ArrayList<Pagopendiente>, context: Context) : RecyclerView.Adapter<PagospendientesAdapter.PagospendientesViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagospendientesViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_pagopendiente, parent, false)
        return PagospendientesViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return pagospendientes.size
    }

    override fun onBindViewHolder(holder: PagospendientesViewHolder, position: Int) {
       val itemnotes = pagospendientes[position]
        holder.bindView(itemnotes)

    }

    inner class PagospendientesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Pagopendiente:Pagopendiente? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(pagospendientes[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Pagopendiente: Pagopendiente){
            this.Pagopendiente = Pagopendiente
           // itemView.Reservaa_item.text = this.Pagopendiente?.ReservaNombre
            var MostrarFecha : String? = this.Pagopendiente?.ReservaNombre
            itemView.Reservaa_item.text = Comun.StringYMDtoDMY(MostrarFecha)
            //itemView.Fechapagoo_item.text = this.Pagopendiente?.FechaPago
            var MostrarFechaPago : String? = this.Pagopendiente?.FechaPago
            itemView.Fechapagoo_item.text = Comun.StringYMDtoDMY(MostrarFechaPago)
            itemView.Tipopagoo_item.text = this.Pagopendiente?.TipopagoNombre
            itemView.Importee_item.text = this.Pagopendiente?.Importe.toString()
//            itemView.Email.text = this.Persona?.Email
        }
    }

    interface OnItemClickListener {
        fun onItemClick(Pagopendiente: Pagopendiente)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}