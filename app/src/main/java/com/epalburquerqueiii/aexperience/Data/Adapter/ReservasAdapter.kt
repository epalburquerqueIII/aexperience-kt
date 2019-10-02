package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Reserva
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_reserva.view.*
import kotlinx.android.synthetic.main.item_reserva.view.IDUsuario_item
import kotlinx.android.synthetic.main.item_reserva.view.IDEspacio_item
import kotlinx.android.synthetic.main.item_reserva.view.IDAutorizado_item


class ReservasAdapter(private val reservas: ArrayList<Reserva>, context: Context) : RecyclerView.Adapter<ReservasAdapter.ReservaViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return reservas.size
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
       val itemnotes = reservas[position]
        holder.bindView(itemnotes)
    }

    inner class ReservaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Reserva:Reserva? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(reservas[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Reserva: Reserva){ 0
            this.Reserva = Reserva
            itemView.Fecha_item.text = this.Reserva?.Fecha
            itemView.FechaPago_item.text = this.Reserva?.FechaPago
            itemView.Hora_item.text = this.Reserva?.Hora.toString()
            itemView.IDUsuario_item.text = this.Reserva?.IDUsuario.toString()
            itemView.IDEspacio_item.text = this.Reserva?.IDEspacio.toString()
            itemView.IDAutorizado_item.text = this.Reserva?.IDAutorizado.toString()
    //To string pendiente de revision
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Reserva: Reserva)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}